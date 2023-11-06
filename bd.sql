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





-- CREACI�N DE TABLAS
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
	objeto NVARCHAR(100) NOT NULL,
	usuario VARCHAR(100) NOT NULL,
	accion VARCHAR(20) NOT NULL,
	modulo VARCHAR(20) NOT NULL,
)

CREATE TABLE tbBitacoraAcceso (
	idBitacoraAcceso INT PRIMARY KEY IDENTITY(1, 1),
	fecha DATE NOT NULL,
	hora TIME NOT NULL,
	usuario VARCHAR(100) NOT NULL
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
		   descripcion AS [Descripci�n]
	FROM tbProducto
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
	AND [Descripci�n] LIKE '%' + @Descripcion + '%'
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





-- TRIGGERS






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
END CATCH


BEGIN TRY
	BEGIN TRANSACTION TranProductos
		INSERT INTO tbProducto (nombre, cantidad, precioCompra, precioVenta, descripcion)
		VALUES (N'Cubo Rubik', 42, 5.00, 25.00, N'Cubo de Rubik')
		INSERT INTO tbProducto (nombre, cantidad, precioCompra, precioVenta, descripcion)
		VALUES (N'Pelota', 100, 5.00, 25.00, N'Pelota antiestr�s')
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
END CATCH