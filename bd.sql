-- BORRAR Y VOLVER A CREAR LA BASE DE DATOS
USE master;
GO

ALTER DATABASE [bdProductAdmin] SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
GO

DROP DATABASE IF EXISTS [bdProductAdmin];
GO

CREATE DATABASE [bdProductAdmin];
GO

USE [bdProductAdmin];
GO





-- CREACIÓN DE TABLAS
CREATE TABLE tbUsuario (
	idUsuario INT PRIMARY KEY IDENTITY(1, 1),
	usuario VARCHAR(100) NOT NULL,
	clave BINARY(64) NOT NULL,
	nombres NVARCHAR(100) NOT NULL,
	apellidos NVARCHAR(100) NOT NULL,
	rol VARCHAR(30) NOT NULL
)

CREATE TABLE tbProducto (
	idProducto INT PRIMARY KEY IDENTITY(1, 1),
	nombre NVARCHAR(100) NOT NULL,
	cantidad INT NOT NULL,
	precioCompra DECIMAL(10, 2) NOT NULL,
	precioVenta DECIMAL(10, 2) NOT NULL,
	descripcion NVARCHAR(250)
)

CREATE TABLE tbCompra (
	idCompra INT PRIMARY KEY IDENTITY(1, 1),
	nombreProducto NVARCHAR(100) NOT NULL,
	precio DECIMAL(10, 2) NOT NULL,
	cantidad INT NOT NULL
)

CREATE TABLE tbVenta (
	idVenta INT PRIMARY KEY IDENTITY(1, 1),
	nombreProducto NVARCHAR(100) NOT NULL,
	precio DECIMAL(10, 2) NOT NULL,
	cantidad INT NOT NULL
)

CREATE TABLE tbBitacoraTransacciones (
	idBitacoraTransacciones INT PRIMARY KEY IDENTITY(1, 1),
	fecha DATE NOT NULL,
	hora TIME NOT NULL,
	objeto NVARCHAR(250) NOT NULL,
	usuario VARCHAR(100) NOT NULL,
	accion VARCHAR(20) NOT NULL,
	modulo VARCHAR(20) NOT NULL
)

CREATE TABLE tbBitacoraAcceso (
	idBitacoraAcceso INT PRIMARY KEY IDENTITY(1, 1),
	fecha DATE NOT NULL,
	hora TIME NOT NULL,
	usuario VARCHAR(100) NOT NULL
)
GO

CREATE TABLE tbUsuarioActivo (
	idUsuarioActivo INT NOT NULL,
	CONSTRAINT FK_idUsuarioActivo FOREIGN KEY (idUsuarioActivo)
	REFERENCES tbUsuario(idUsuario)
)
GO





-- VISTAS
CREATE VIEW vUsuarios
AS
	SELECT idUsuario AS [ID Usuario],
		   usuario AS [Usuario],
		   nombres AS [Nombres],
		   apellidos AS [Apellidos],
		   rol AS [Rol]
	FROM tbUsuario
GO

CREATE VIEW vProductos
AS
	SELECT idProducto AS [ID Producto],
		   nombre AS [Nombre],
		   cantidad AS [Cantidad],
		   precioCompra AS [Precio Compra],
		   precioVenta AS [Precio Venta],
		   descripcion AS [Descripción]
	FROM tbProducto
GO

CREATE VIEW vCompras
AS
	SELECT nombreProducto AS [Nombre],
		   precio AS [Precio],
		   cantidad AS [Cantidad],
		   precio * cantidad AS [Total]
	FROM tbCompra
GO

CREATE VIEW vVentas
AS
	SELECT nombreProducto AS [Nombre],
		   precio AS [Precio],
		   cantidad AS [Cantidad],
		   precio * cantidad AS [Total]
	FROM tbVenta
GO

CREATE VIEW vBitacoraTransacciones
AS
	SELECT fecha AS [Fecha],
		   CONVERT(CHAR(5), hora, 108) AS [Hora],
		   objeto AS [Objeto],
		   usuario AS [Usuario],
		   accion AS [Acción],
		   modulo AS [Módulo]
	FROM tbBitacoraTransacciones
GO

CREATE VIEW vBitacoraAcceso
AS
	SELECT fecha AS [Fecha],
		   CONVERT(CHAR(5), hora, 108) AS [Hora],
		   usuario AS [Usuario]
	FROM tbBitacoraAcceso
GO





-- PROCEDIMIENTOS
CREATE PROCEDURE pCrearUsuario
	@Usuario VARCHAR(100),
	@Clave VARCHAR(MAX),
	@Nombres NVARCHAR(100),
	@Apellidos NVARCHAR(100),
	@Rol VARCHAR(30)
AS BEGIN
	BEGIN TRY
		BEGIN TRAN
			INSERT INTO tbUsuario (usuario, clave, nombres, apellidos, rol)
			VALUES (@Usuario, dbo.fHash(@Clave), @Nombres, @Apellidos, @Rol)
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		IF (@@TRANCOUNT > 0)
		BEGIN
			ROLLBACK TRAN
			PRINT 'Error detectado al momento de agregar un usuario.'
		END
	END CATCH
END
GO

CREATE PROCEDURE pModificarUsuario
	@IDUsuario INT,
	@Usuario VARCHAR(100),
	@Nombres NVARCHAR(100),
	@Apellidos NVARCHAR(100),
	@Rol VARCHAR(30)
AS BEGIN
	BEGIN TRY
		BEGIN TRAN
			UPDATE tbUsuario SET
				usuario = @Usuario,
				nombres = @Nombres,
				apellidos = @Apellidos,
				rol = @Rol
			WHERE idUsuario = @IDUsuario
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		IF (@@TRANCOUNT > 0)
		BEGIN
			ROLLBACK TRAN
			PRINT 'Error detectado al momento de modificar un usuario.'
		END
	END CATCH
END
GO

CREATE PROCEDURE pEliminarUsuario
	 @IDUsuario INT
AS BEGIN
	BEGIN TRY
		BEGIN TRAN
			DELETE FROM tbUsuario
			WHERE idUsuario = @IDUsuario
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		IF (@@TRANCOUNT > 0)
		BEGIN
			ROLLBACK TRAN
			PRINT 'Error detectado al momento de eliminar un usuario.'
		END
	END CATCH
END
GO

CREATE PROCEDURE pBuscarUsuario
	@IDUsuario VARCHAR(MAX),
	@Usuario VARCHAR(100),
	@Nombres NVARCHAR(100),
	@Apellidos NVARCHAR(100),
	@Rol NVARCHAR(30)
AS BEGIN
	SELECT *
	FROM vUsuarios
	WHERE [ID Usuario] LIKE '%' + @IDUsuario + '%'
	AND [Usuario] LIKE '%' + @Usuario + '%'
	AND [Nombres] LIKE '%' + @Nombres + '%'
	AND [Apellidos] LIKE '%' + @Apellidos + '%'
	AND [Rol] LIKE '%' + @Rol + '%'
END
GO

CREATE PROCEDURE pConsultarUsuario
	@IDUsuario INT
AS BEGIN
	SELECT *
	FROM vUsuarios
	WHERE [ID Usuario] = @IDUsuario
END
GO

CREATE PROCEDURE pCambiarClave
	@IDUsuario INT,
	@ClaveNueva NVARCHAR(MAX)
AS BEGIN
	UPDATE tbUsuario SET
		clave = dbo.fHash(@ClaveNueva)
	WHERE idUsuario = @IDUsuario
END
GO

CREATE PROCEDURE pLogin
	@Usuario VARCHAR(100),
	@Clave NVARCHAR(MAX)
AS BEGIN
	DECLARE @IDUsuario INT
	SET @IDUsuario = dbo.fLogin(@Usuario, @Clave)
	IF @IDUsuario = 0 SELECT 0
	ELSE BEGIN
		BEGIN TRY
			BEGIN TRAN
				INSERT INTO tbBitacoraAcceso (fecha, hora, usuario)
				VALUES (CAST(GETDATE() AS DATE),
						CAST(GETDATE() AS TIME),
						(SELECT usuario
						FROM tbUsuario
						WHERE idUsuario = @IDUsuario))
			COMMIT TRAN
			SELECT @IDUsuario
		END TRY
		BEGIN CATCH
			IF (@@TRANCOUNT > 0)
			BEGIN
				ROLLBACK TRAN
				PRINT 'Error detectado al momento de agregar un registro de bitácora de transacciones.'
			END
		END CATCH
	END
END
GO





CREATE PROCEDURE pCrearProducto
	@Nombre NVARCHAR(100),
	@Cantidad INT,
	@PrecioCompra DECIMAL(10, 2),
	@PrecioVenta DECIMAL(10, 2),
	@Descripcion NVARCHAR(250)
AS BEGIN
	BEGIN TRY
		BEGIN TRAN
			INSERT INTO tbProducto (nombre, cantidad, precioCompra, precioVenta, descripcion)
			VALUES (@Nombre, @Cantidad, @PrecioCompra, @PrecioVenta, @Descripcion)
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		IF (@@TRANCOUNT > 0)
		BEGIN
			ROLLBACK TRAN
			PRINT 'Error detectado al momento de agregar un producto.'
		END
	END CATCH
END
GO

CREATE PROCEDURE pModificarProducto
	@IDProducto INT,
	@Nombre NVARCHAR(100),
	@Cantidad INT,
	@PrecioCompra DECIMAL(10, 2),
	@PrecioVenta DECIMAL(10, 2),
	@Descripcion NVARCHAR(250)
AS BEGIN
	BEGIN TRY
		BEGIN TRAN
			UPDATE tbProducto SET
				nombre = @Nombre,
				cantidad = @Cantidad,
				precioCompra = @PrecioCompra,
				precioVenta = @PrecioVenta,
				descripcion = @Descripcion
			WHERE idProducto = @IDProducto
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		IF (@@TRANCOUNT > 0)
		BEGIN
			ROLLBACK TRAN
			PRINT 'Error detectado al momento de modificar un producto.'
		END
	END CATCH
END
GO

CREATE PROCEDURE pEliminarProducto
	 @IDProducto INT
AS BEGIN
	BEGIN TRY
		BEGIN TRAN
			DELETE FROM tbProducto
			WHERE idProducto = @IDProducto
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		IF (@@TRANCOUNT > 0)
		BEGIN
			ROLLBACK TRAN
			PRINT 'Error detectado al momento de eliminar un producto.'
		END
	END CATCH
END
GO

CREATE PROCEDURE pBuscarProducto
	@IDProducto VARCHAR(MAX),
	@Nombre NVARCHAR(100),
	@Cantidad VARCHAR(MAX),
	@PrecioCompra VARCHAR(MAX),
	@PrecioVenta VARCHAR(MAX),
	@Descripcion NVARCHAR(250)
AS BEGIN
	SELECT *
	FROM vProductos
	WHERE [ID Producto] LIKE '%' + @IDProducto + '%'
	AND [Nombre] LIKE '%' + @Nombre + '%'
	AND [Cantidad] LIKE '%' + @Cantidad + '%'
	AND [Precio Compra] LIKE '%' + @PrecioCompra + '%'
	AND [Precio Venta] LIKE '%' + @PrecioVenta + '%'
	AND [Descripción] LIKE '%' + @Descripcion + '%'
END
GO

CREATE PROCEDURE pConsultarProducto
	@IDProducto INT
AS BEGIN
	SELECT *
	FROM vProductos
	WHERE [ID Producto] = @IDProducto
END
GO

CREATE PROCEDURE pRegistrarOperacion
	@NombreProducto NVARCHAR(100),
	@TipoOperacion VARCHAR(10),
	@Precio DECIMAL(10, 2),
	@Cantidad INT
AS BEGIN
	BEGIN TRY
		BEGIN TRAN
			IF @TipoOperacion = 'Compra'
			BEGIN
				INSERT INTO tbCompra (nombreProducto, precio, cantidad)
				VALUES (@NombreProducto, @Precio, @Cantidad)
			END
			ELSE
			BEGIN
				INSERT INTO tbVenta (nombreProducto, precio, cantidad)
				VALUES (@NombreProducto, @Precio, @Cantidad)
			END
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		IF (@@TRANCOUNT > 0)
		BEGIN
			ROLLBACK TRAN
			PRINT 'Error detectado al momento de agregar una operación.'
		END
	END CATCH
END
GO



CREATE PROCEDURE pBuscarRegistroTransaccion
	@FechaInicial VARCHAR(20),
	@FechaFinal VARCHAR(20),
	@HoraInicial VARCHAR(10),
	@HoraFinal VARCHAR(10),
	@Objeto NVARCHAR(MAX),
	@Usuario VARCHAR(MAX),
	@Accion VARCHAR(20),
	@Modulo VARCHAR(20)
AS BEGIN
	SELECT *
	FROM vBitacoraTransacciones
	WHERE [Fecha] BETWEEN @FechaInicial AND @FechaFinal
	AND [Hora] BETWEEN @HoraInicial AND @HoraFinal
	AND [Objeto] LIKE '%' + @Objeto + '%'
	AND [Usuario] LIKE '%' + @Usuario + '%'
	AND [Acción] LIKE '%' + @Accion + '%'
	AND [Módulo] LIKE '%' + @Modulo + '%'
END
GO

CREATE PROCEDURE pBuscarRegistroAcceso
	@FechaInicial VARCHAR(20),
	@FechaFinal VARCHAR(20),
	@HoraInicial VARCHAR(10),
	@HoraFinal VARCHAR(10),
	@Usuario VARCHAR(MAX)
AS BEGIN
	SELECT *
	FROM vBitacoraAcceso
	WHERE [Fecha] BETWEEN @FechaInicial AND @FechaFinal
	AND [Hora] BETWEEN @HoraInicial AND @HoraFinal
	AND [Usuario] LIKE '%' + @Usuario + '%'
END
GO





-- FUNCIONES
CREATE FUNCTION fHash(@String NVARCHAR(MAX))
RETURNS BINARY(64)
AS BEGIN
	RETURN HASHBYTES('SHA2_256', @String)
END
GO

CREATE FUNCTION fLogin(@Usuario VARCHAR(100), @Clave NVARCHAR(MAX))
RETURNS INT
AS BEGIN
	DECLARE @IDUsuario INT
	SET @IDUsuario = (SELECT idUsuario
					  FROM tbUsuario
					  WHERE usuario = @Usuario
					  AND clave = dbo.fHash(@Clave))
	IF @IDUsuario IS NULL SET @IDUsuario = 0
	RETURN @IDUsuario
END
GO

CREATE FUNCTION fComprobarClave(@IDUsuario INT, @Clave NVARCHAR(MAX))
RETURNS BIT
AS BEGIN
	DECLARE @ReturnState INT
	DECLARE @ClaveBD BINARY(64)
	SET @ClaveBD = (SELECT clave
					FROM tbUsuario
					WHERE idUsuario = @IDUsuario)
	IF @ClaveBD = dbo.fHash(@Clave)
		SET @ReturnState = 1
	ELSE
		SET @ReturnState = 0
	RETURN @ReturnState
END
GO

CREATE FUNCTION fObtenerUsuarioActivo()
RETURNS VARCHAR(100)
AS BEGIN
	DECLARE @Usuario VARCHAR(100)
	SET @Usuario = (SELECT usuario
					FROM tbBitacoraAcceso
					WHERE idBitacoraAcceso = (SELECT MAX(idBitacoraAcceso)
											  FROM tbBitacoraAcceso))
	IF @Usuario IS NULL SET @Usuario = 'ProductAdmin'
	RETURN @Usuario
END
GO





-- ADMIN ACCOUNT
EXEC pCrearUsuario 'admin', 'admin', N'admin', N'admin', 'Administrador'
GO





-- TRIGGERS
CREATE TRIGGER tBitacoraCrearUsuario
ON tbUsuario
AFTER INSERT
AS
BEGIN TRY
	BEGIN TRAN
		INSERT INTO tbBitacoraTransacciones(fecha, hora, objeto, usuario, accion, modulo)
		SELECT CAST(GETDATE() AS DATE),
			CAST(GETDATE() AS TIME),
			usuario,
			dbo.fObtenerUsuarioActivo(),
			'Crear',
			'Usuario'
		FROM INSERTED
	COMMIT TRAN
END TRY
BEGIN CATCH
	IF (@@TRANCOUNT > 0)
	BEGIN
		ROLLBACK TRAN 
		PRINT 'Error detectado al momento de agregar un registro de bitácora de transacciones.'
	END
END CATCH
GO

CREATE TRIGGER tBitacoraModificarUsuario
ON tbUsuario
AFTER UPDATE
AS
BEGIN TRY
	BEGIN TRAN
		INSERT INTO tbBitacoraTransacciones(fecha, hora, objeto, usuario, accion, modulo)
		SELECT CAST(GETDATE() AS DATE),
			   CAST(GETDATE() AS TIME),
			   usuario,
			   dbo.fObtenerUsuarioActivo(),
			   'Modificar',
			   'Usuarios'
		FROM INSERTED
	COMMIT TRAN
END TRY
BEGIN CATCH
	IF (@@TRANCOUNT > 0)
	BEGIN
		ROLLBACK TRAN 
		PRINT 'Error detectado al momento de modificar un registro de bitácora de transacciones.'
	END
END CATCH
GO

CREATE TRIGGER tBitacoraEliminarUsuario
ON tbUsuario
AFTER DELETE
AS
BEGIN TRY
	BEGIN TRAN
		DECLARE @IDUsuario INT
		SET @IDUsuario = (SELECT @IDUsuario FROM DELETED)
	
		INSERT INTO tbBitacoraTransacciones(fecha, hora, objeto, usuario, accion, modulo)
		SELECT CAST(GETDATE() AS DATE),
			   CAST(GETDATE() AS TIME),
			   usuario,
			   dbo.fObtenerUsuarioActivo(),
			   'Eliminar',
			   'Usuarios'
		FROM DELETED
	COMMIT TRAN
END TRY
BEGIN CATCH
	IF (@@TRANCOUNT > 0)
	BEGIN
		ROLLBACK TRAN 
		PRINT 'Error detectado al momento de eliminar un registro de bitácora de transacciones.'
	END
END CATCH
GO



CREATE TRIGGER tBitacoraCrearProducto
ON tbProducto
AFTER INSERT
AS
BEGIN TRY 
	BEGIN TRAN
		INSERT INTO tbBitacoraTransacciones(fecha, hora, objeto, usuario, accion, modulo)
		SELECT CAST(GETDATE() AS DATE),
			   CAST(GETDATE() AS TIME),
			   nombre,
			   dbo.fObtenerUsuarioActivo(),
			   'Crear',
			   'Productos'
		FROM INSERTED
	COMMIT TRAN
END TRY
BEGIN CATCH
	IF (@@TRANCOUNT > 0)
	BEGIN
		ROLLBACK TRAN 
		PRINT 'Error detectado al momento de agregar un registro de bitácora de transacciones.'
	END
END CATCH
GO

CREATE TRIGGER tBitacoraModificarProducto
ON tbProducto
AFTER UPDATE
AS
BEGIN TRY
	BEGIN TRAN
		INSERT INTO tbBitacoraTransacciones(fecha, hora, objeto, usuario, accion, modulo)
		SELECT CAST(GETDATE() AS DATE),
			   CAST(GETDATE() AS TIME),
			   nombre,
			dbo.fObtenerUsuarioActivo(),
			'Modificar',
			'Productos'
		FROM INSERTED
	COMMIT TRAN
END TRY
BEGIN CATCH
	IF (@@TRANCOUNT > 0)
	BEGIN
		ROLLBACK TRAN 
		PRINT 'Error detectado al momento de modificar un registro de bitácora de transacciones.'
	END
END CATCH
GO

CREATE TRIGGER tBitacoraEliminarProducto
ON tbProducto
AFTER DELETE
AS
BEGIN TRY
	BEGIN TRAN
		DECLARE @IDProducto INT
		SET @IDProducto = (SELECT idProducto FROM DELETED)
	
		INSERT INTO tbBitacoraTransacciones(fecha, hora, objeto, usuario, accion, modulo)
		SELECT CAST(GETDATE() AS DATE),
			   CAST(GETDATE() AS TIME),
			   nombre,
			   dbo.fObtenerUsuarioActivo(),
			   'Eliminar',
			   'Productos'
		FROM DELETED
	COMMIT TRAN
END TRY
BEGIN CATCH
	IF (@@TRANCOUNT > 0)
	BEGIN
		ROLLBACK TRAN 
		PRINT 'Error detectado al momento de eliminar un registro de bitácora de transacciones.'
	END
END CATCH
GO





-- INSERT VALUES
EXEC pLogin 'admin', 'admin'
EXEC pCrearProducto N'Cubo Rubik', 42, 5.00, 25.00, N'Cubo de Rubik'
EXEC pCrearProducto N'Pelota', 100, 5.00, 25.00, N'Pelota antiestrés'
EXEC pCrearProducto N'Pulsera', 0, 1.00, 3.00, ''
GO