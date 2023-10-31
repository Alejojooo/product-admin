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
	nombreProducto NVARCHAR(100) NOT NULL,
	cantidad INT NOT NULL,
	precioCompra DECIMAL(10, 2) NOT NULL,
	precioVenta DECIMAL(10, 2) NOT NULL,
	descripcion NVARCHAR(250)
)

CREATE TABLE tbOperacion (
	idOperacion INT PRIMARY KEY IDENTITY(1, 1),
	nombreProducto NVARCHAR(100) NOT NULL,
	tipoOperacion VARCHAR(10) NOT NULL,
	cantidad INT NOT NULL,
	precio DECIMAL(10, 2) NOT NULL
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
		   nombreProducto AS [Nombre],
		   cantidad AS [Cantidad],
		   precioCompra AS [Precio Compra],
		   precioVenta AS [Precio Venta],
		   descripcion AS [Descripción]
	FROM tbProducto
GO





-- FUNCIONES
CREATE FUNCTION fHash (@String VARCHAR(MAX))
RETURNS BINARY(64)
AS BEGIN
	RETURN HASHBYTES('SHA2_256', @String)
END
GO

CREATE FUNCTION fLogin (@Usuario VARCHAR(100), @Clave VARCHAR(MAX))
RETURNS INT
AS BEGIN
	DECLARE @IDUsuario INT
	SET @IDUsuario = (SELECT idUsuario
					  FROM tbUsuario
					  WHERE usuario = @Usuario
					  AND clave = dbo.fHash(@Clave))
	RETURN @IDUsuario
END
GO

CREATE FUNCTION fComprobarClave (@IDUsuario INT, @Clave VARCHAR(MAX))
RETURNS INT
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



/*
CREATE FUNCTION fBuscarUsuario (
	@IDUsuario INT,
	@Usuario VARCHAR(100),
	@Nombres NVARCHAR(100),
	@Apellidos NVARCHAR(100),
	@Rol NVARCHAR(30))
RETURNS TABLE
AS RETURN
	SELECT *
	FROM vUsuarios
	WHERE [ID Usuario] LIKE '%' + @IDUsuario + '%'
	OR [Usuario] LIKE '%' + @Usuario + '%'
	OR [Nombres] LIKE '%' + @Nombres + '%'
	OR [Apellidos] LIKE '%' + @Apellidos + '%'
	OR [Rol] LIKE '%' + @Rol + '%'
GO

CREATE FUNCTION fBuscarProducto (
	@IDProducto INT,
	@Nombre NVARCHAR(100),
	@Cantidad INT,
	@PrecioCompra DECIMAL(10, 2),
	@PrecioVenta DECIMAL(10, 2),
	@Descripcion NVARCHAR(250))
RETURNS TABLE
AS RETURN
	SELECT *
	FROM vProductos
	WHERE [ID Producto] LIKE '%' + @IDProducto + '%'
	OR [Nombre] LIKE '%' + @Nombre + '%'
	OR [Cantidad] LIKE '%' + @Cantidad + '%'
	OR [Precio Compra] LIKE '%' + @PrecioCompra + '%'
	OR [Precio Venta] LIKE '%' + @PrecioVenta + '%'
	OR [Descripción] LIKE '%' + @Descripcion + '%'
GO
*/





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

CREATE PROCEDURE pCambiarClave
	@IDUsuario INT,
	@ClaveVieja VARCHAR(MAX),
	@ClaveNueva VARCHAR(MAX)
AS BEGIN
	IF (SELECT dbo.fComprobarClave(@IDUsuario, @ClaveVieja)) > 0
	BEGIN
		UPDATE tbUsuario SET
			clave = dbo.fHash(@ClaveNueva)
		WHERE idUsuario = @IDUsuario
	END
END
GO



CREATE PROCEDURE pCrearProducto
	@NombreProducto NVARCHAR(100),
	@Cantidad INT,
	@PrecioCompra DECIMAL(10, 2),
	@PrecioVenta DECIMAL(10, 2),
	@Descripcion NVARCHAR(250)
AS BEGIN
	INSERT INTO tbProducto (nombreProducto, cantidad, precioCompra, precioVenta, descripcion)
	VALUES (@NombreProducto, @Cantidad, @PrecioCompra, @PrecioVenta, @Descripcion)
END
GO

CREATE PROCEDURE pModificarProducto
	@IDProducto INT,
	@NombreProducto NVARCHAR(100),
	@Cantidad INT,
	@PrecioCompra DECIMAL(10, 2),
	@PrecioVenta DECIMAL(10, 2),
	@Descripcion NVARCHAR(250)
AS BEGIN
	UPDATE tbProducto SET
		nombreProducto = @NombreProducto,
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

CREATE PROCEDURE pRegistrarOperacion
	@NombreProducto NVARCHAR(100),
	@TipoOperacion VARCHAR(10),
	@Cantidad INT,
	@Precio DECIMAL(10, 2)
AS BEGIN
	INSERT INTO tbOperacion (nombreProducto, tipoOperacion, cantidad, precio)
	VALUES (@NombreProducto, @TipoOperacion, @Cantidad, @Precio)
END
GO





-- TRIGGERS






-- INSERT VALUES
BEGIN TRY
	BEGIN TRANSACTION TranUsuarios
		INSERT INTO tbUsuario (usuario, clave, nombres, apellidos, rol)
		VALUES ('admin', dbo.fHash('admin'), N'admin', N'admin', 'ADMINISTRADOR')
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
		INSERT INTO tbProducto (nombreProducto, cantidad, precioCompra, precioVenta, descripcion)
		VALUES (N'Cubo Rubik', 42, 5.00, 25.00, N'Cubo de Rubik')
		INSERT INTO tbProducto (nombreProducto, cantidad, precioCompra, precioVenta, descripcion)
		VALUES (N'Pelota', 100, 5.00, 25.00, N'Pelota antiestrés')
		INSERT INTO tbProducto (nombreProducto, cantidad, precioCompra, precioVenta, descripcion)
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