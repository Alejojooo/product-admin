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

CREATE TABLE tbOperacion (
	idOperacion INT PRIMARY KEY IDENTITY(1, 1),
	nombreProducto NVARCHAR(100) NOT NULL,
	tipoOperacion VARCHAR(10) NOT NULL,
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

CREATE VIEW vBitacoraTransacciones
AS
	SELECT fecha AS [Fecha],
		   hora AS [Hora],
		   objeto AS [Objeto],
		   usuario AS [Usuario],
		   accion AS [Acción],
		   modulo AS [Módulo]
	FROM tbBitacoraTransacciones
GO

CREATE VIEW vBitacoraAcceso
AS
	SELECT fecha AS [Fecha],
		   hora AS [Hora],
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
	INSERT INTO tbUsuario (usuario, clave, nombres, apellidos, rol)
	VALUES (@Usuario, dbo.fHash(@Clave), @Nombres, @Apellidos, @Rol)
END
GO

CREATE PROCEDURE pModificarUsuario
	@IDUsuario INT,
	@Usuario VARCHAR(100),
	@Nombres NVARCHAR(100),
	@Apellidos NVARCHAR(100),
	@Rol VARCHAR(30)
AS BEGIN
	UPDATE tbUsuario SET
	usuario = @Usuario,
	nombres = @Nombres,
	apellidos = @Apellidos,
	rol = @Rol
	WHERE idUsuario = @IDUsuario
END
GO

CREATE PROCEDURE pEliminarUsuario
	 @IDUsuario INT
AS BEGIN
	DELETE FROM tbUsuario
	WHERE idUsuario = @IDUsuario
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
	OR [Usuario] LIKE '%' + @Usuario + '%'
	OR [Nombres] LIKE '%' + @Nombres + '%'
	OR [Apellidos] LIKE '%' + @Apellidos + '%'
	OR [Rol] LIKE '%' + @Rol + '%'
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
	DECLARE @Login BIT
	SET @IDUsuario = dbo.fLogin(@Usuario, @Clave)
	IF @Login = 0 SELECT 0
	ELSE BEGIN
		INSERT INTO tbBitacoraAcceso (fecha, hora, usuario)
		VALUES (CAST(GETDATE() AS DATE),
				CAST(GETDATE() AS TIME),
				(SELECT usuario
				FROM tbUsuario
				WHERE idUsuario = @IDUsuario))
		SELECT @IDUsuario
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
	INSERT INTO tbProducto (nombre, cantidad, precioCompra, precioVenta, descripcion)
	VALUES (@Nombre, @Cantidad, @PrecioCompra, @PrecioVenta, @Descripcion)
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
	UPDATE tbProducto SET
		nombre = @Nombre,
		cantidad = @Cantidad,
		precioCompra = @PrecioCompra,
		precioVenta = @PrecioVenta,
		descripcion = @Descripcion
	WHERE idProducto = @IDProducto
END
GO

CREATE PROCEDURE pEliminarProducto
	 @IDProducto INT
AS BEGIN
	DELETE FROM tbProducto
	WHERE idProducto = @IDProducto
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
	INSERT INTO tbOperacion (nombreProducto, tipoOperacion, precio, cantidad)
	VALUES (@NombreProducto, @TipoOperacion, @Precio, @Cantidad)
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
CREATE FUNCTION fHash (@String NVARCHAR(MAX))
RETURNS BINARY(64)
AS BEGIN
	RETURN HASHBYTES('SHA2_256', @String)
END
GO

CREATE FUNCTION fLogin (@Usuario VARCHAR(100), @Clave NVARCHAR(MAX))
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

CREATE FUNCTION fComprobarClave (@IDUsuario INT, @Clave NVARCHAR(MAX))
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





-- TRIGGERS
CREATE TRIGGER tBitacoraCrearProducto
ON tbProducto
INSTEAD OF INSERT
AS
BEGIN
	INSERT INTO tbBitacoraTransacciones(fecha, hora, objeto, usuario, accion, modulo)
	SELECT CAST(GETDATE() AS DATE),
		   CAST(GETDATE() AS TIME),
		   '[' + CAST(idProducto AS VARCHAR) + '] ' + nombre,
		   (SELECT usuario
		    FROM tbBitacoraAcceso
		    WHERE idBitacoraAcceso = (SELECT MAX(idBitacoraAcceso)
									  FROM tbBitacoraAcceso)),
		   'Crear',
		   'Productos'
	FROM INSERTED

	INSERT INTO tbProducto (nombre, cantidad, precioCompra, precioVenta, descripcion)
	SELECT nombre, cantidad, precioCompra, precioVenta, descripcion
	FROM INSERTED
END
GO

CREATE TRIGGER tBitacoraModificarProducto
ON tbProducto
INSTEAD OF UPDATE
AS
BEGIN
	INSERT INTO tbBitacoraTransacciones(fecha, hora, objeto, usuario, accion, modulo)
	SELECT CAST(GETDATE() AS DATE),
		   CAST(GETDATE() AS TIME),
		   '[' + CAST(idProducto AS VARCHAR) + '] ' + nombre,
		   (SELECT usuario
		    FROM tbBitacoraAcceso
		    WHERE idBitacoraAcceso = (SELECT MAX(idBitacoraAcceso)
									  FROM tbBitacoraAcceso)),
		   'Modificar',
		   'Productos'
	FROM INSERTED

	UPDATE tbProducto SET
		nombre = i.nombre,
		cantidad = i.cantidad,
		precioCompra = i.precioCompra,
		precioVenta = i.precioVenta,
		descripcion = i.descripcion
	FROM INSERTED AS i
	WHERE tbProducto.idProducto = i.idProducto
END
GO

CREATE TRIGGER tBitacoraEliminarProducto
ON tbProducto
INSTEAD OF DELETE
AS
BEGIN
	DECLARE @IDProducto INT
	SET @IDProducto = (SELECT idProducto FROM DELETED)

	INSERT INTO tbBitacoraTransacciones(fecha, hora, objeto, usuario, accion, modulo)
	SELECT CAST(GETDATE() AS DATE),
		   CAST(GETDATE() AS TIME),
		   '[' + CAST(idProducto AS VARCHAR) + '] ' + nombre,
		   (SELECT usuario
		    FROM tbBitacoraAcceso
		    WHERE idBitacoraAcceso = (SELECT MAX(idBitacoraAcceso)
									  FROM tbBitacoraAcceso)),
		   'Eliminar',
		   'Productos'
	FROM DELETED

	DELETE FROM tbProducto
	WHERE idProducto = @IDProducto
END
GO





-- INSERT VALUES
BEGIN TRY
	BEGIN TRANSACTION TranUsuarios
		INSERT INTO tbUsuario (usuario, clave, nombres, apellidos, rol)
		VALUES ('admin', dbo.fHash('admin'), N'admin', N'admin', 'Administrador')
	COMMIT TRANSACTION TranUsuarios
	PRINT 'Ingresados usuarios.'
END TRY
BEGIN CATCH 
	IF (@@TRANCOUNT > 0)
	BEGIN
		ROLLBACK TRANSACTION TranUsuarios
		PRINT 'Error detectado al momento de ingresar usuarios.'
	END
	SELECT
		ERROR_NUMBER() AS ErrorNumber,
		ERROR_SEVERITY() AS ErrorSeverity,
		ERROR_STATE() AS ErrorState,
		ERROR_PROCEDURE() AS ErrorProcedure,
		ERROR_LINE() AS ErrorLine,
		ERROR_MESSAGE() AS ErrorMessage
END CATCH



EXEC pLogin 'admin', 'admin'
BEGIN TRY
	BEGIN TRANSACTION TranProductos
		INSERT INTO tbProducto (nombre, cantidad, precioCompra, precioVenta, descripcion)
		VALUES (N'Cubo Rubik', 42, 5.00, 25.00, N'Cubo de Rubik')
		INSERT INTO tbProducto (nombre, cantidad, precioCompra, precioVenta, descripcion)
		VALUES (N'Pelota', 100, 5.00, 25.00, N'Pelota antiestrés')
		INSERT INTO tbProducto (nombre, cantidad, precioCompra, precioVenta, descripcion)
		VALUES (N'Pulsera', 0, 1.00, 3.00, '')
	COMMIT TRANSACTION TranProductos
	PRINT 'Ingresados productos.'
END TRY
BEGIN CATCH 
	IF (@@TRANCOUNT > 0)
	BEGIN
		ROLLBACK TRANSACTION TranProductos
		PRINT 'Error detectado al momento de ingresar productos.'
	END
	SELECT
		ERROR_NUMBER() AS ErrorNumber,
		ERROR_SEVERITY() AS ErrorSeverity,
		ERROR_STATE() AS ErrorState,
		ERROR_PROCEDURE() AS ErrorProcedure,
		ERROR_LINE() AS ErrorLine,
		ERROR_MESSAGE() AS ErrorMessage
END CATCH