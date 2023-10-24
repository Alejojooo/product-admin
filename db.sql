IF NOT EXISTS (
		SELECT *
		FROM sys.databases
		WHERE name = 'dbProductAdmin'
		)
BEGIN
	CREATE DATABASE [dbProductAdmin]
END
GO

USE [dbProductAdmin]
GO

IF NOT EXISTS (
		SELECT *
		FROM sysobjects
		WHERE name = 'tbRol'
			AND xtype = 'U'
		)
BEGIN
	CREATE TABLE tbRol (
		id INT PRIMARY KEY IDENTITY(1, 1),
		nombre VARCHAR(100),
		descripcion VARCHAR(200)
		)

	CREATE TABLE tbUsuario (id)
END
