USE [master]
GO
/****** Object:  Database [school]    Script Date: 23/03/2016 13:56:02 ******/
CREATE DATABASE [school2]

GO
USE [school2]
GO
/****** Object:  StoredProcedure [dbo].[Administrador_Create]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[Administrador_Create]
	@primer_nombre 		NVARCHAR(50),
	@segundo_nombre 	NVARCHAR(50) = NULL,
	@apellido_paterno 	NVARCHAR(50),
	@apellido_materno 	NVARCHAR(50)= NULL,
	@direccion1 		NVARCHAR(50),
	@direccion2 		NVARCHAR(50)= NULL,
	@telefono 			NVARCHAR(50)= NULL,
	@celular 			NVARCHAR(50),
	@email_principal 	NVARCHAR(50),
	@email_secundario 	NVARCHAR(50)= NULL,
	@usuario 			NVARCHAR(50),
	@passw 				NVARCHAR(50),
	@estado 			INT,

	@cargo 				NVARCHAR(50)
	--@CursoGuid UNIQUEIDENTIFIER
AS
BEGIN
	DECLARE @PersonaGuid UNIQUEIDENTIFIER = NEWID()
	DECLARE @administradorGuid UNIQUEIDENTIFIER = NEWID()

	INSERT INTO Persona (id_persona, nombre1, nombre2, apellido_paterno, apellido_materno,
				email1,emal2, fono, cel,dir1,dir2, usuario, pass, estado)
	VALUES	(@PersonaGuid, @primer_nombre, @segundo_nombre, @apellido_paterno, @apellido_materno, 
				@email_principal, @email_secundario, @telefono, @celular, @direccion1, @direccion2, @usuario, @passw, @estado)

    INSERT INTO Administrador(id_administrador,cargo,id_persona)
	VALUES (@administradorGuid,@cargo,@PersonaGuid)

	SELECT id_administrador
	FROM Administrador
	WHERE id_administrador = @administradorGuid
END



GO
/****** Object:  StoredProcedure [dbo].[AdministradorDelete]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[AdministradorDelete]

	@AdministradorGuid UNIQUEIDENTIFIER,
	@estado 			INT
AS 
BEGIN
	UPDATE Persona 
	SET estado = @estado
	FROM Persona per
		JOIN Administrador adm
			ON per.id_persona = adm.id_persona AND adm.id_administrador = @AdministradorGuid
END

GO
/****** Object:  StoredProcedure [dbo].[AdministradorRead]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[AdministradorRead]

	@id_administrador UNIQUEIDENTIFIER
AS 
BEGIN

	SELECT per.nombre1, per.nombre2, per.apellido_paterno, per.apellido_materno,per.dir1, 
			per.dir2, per.email1,per.emal2, per.fono,per.cel,per.usuario,per.estado
	FROM Persona per
		JOIN Administrador adm
		ON per.id_persona= adm.id_persona AND adm.id_administrador = @id_administrador
END

GO
/****** Object:  StoredProcedure [dbo].[AdministradorUpdate]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[AdministradorUpdate]

	@AdministradorGuid UNIQUEIDENTIFIER,
	@cargo 				NVARCHAR(50),

	@primer_nombre 		NVARCHAR(50),
	@segundo_nombre 	NVARCHAR(50) = NULL,
	@apellido_paterno 	NVARCHAR(50),
	@apellido_materno 	NVARCHAR(50)= NULL,
	@direccion1 		NVARCHAR(50),
	@direccion2 		NVARCHAR(50)= NULL,
	@telefono 			NVARCHAR(50)= NULL,
	@celular 			NVARCHAR(50),
	@email_principal 	NVARCHAR(50),
	@email_secundario 	NVARCHAR(50)= NULL
	--@usuario 			NVARCHAR(50),
	--@passw 				NVARCHAR(50),
	--@estado 			INT,
	
	
	
AS 
BEGIN
	UPDATE Persona 
	SET nombre1 = @primer_nombre ,nombre2 = @segundo_nombre, apellido_paterno = @apellido_paterno , apellido_materno = @apellido_materno,
	dir1 = @direccion1, dir2 = @direccion2, fono = @telefono, cel = @celular, email1 = @email_principal, emal2 = @email_secundario
	
	FROM Persona per
		JOIN Administrador adm
			ON per.id_persona = adm.id_persona AND adm.id_administrador = @AdministradorGuid AND cargo = @cargo
END

GO
/****** Object:  StoredProcedure [dbo].[curso_Create]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[curso_Create]
	
		
		@nombre NVARCHAR(50),
		@grado  NVARCHAR(50),
		@nivel  INT

AS 
BEGIN
	DECLARE @CursoGuid UNIQUEIDENTIFIER = NEWID()

	INSERT INTO Curso (id_curso,nombre,grado, nivel)
	VALUES (@CursoGuid,@nombre, @grado,@nivel)


END


GO
/****** Object:  StoredProcedure [dbo].[Curso_Get]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Curso_Get] 

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
		SELECT id_curso, nivel,grado , nombre
		FROM Curso
		ORDER BY nivel, grado
END
GO
/****** Object:  StoredProcedure [dbo].[Estudiante_Create]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Estudiante_Create]
	@primer_nombre		NVARCHAR(50),
	@segundo_nombre		NVARCHAR(50) = NULL,
	@apellido_paterno   NVARCHAR(50),
	@apellido_materno   NVARCHAR(50)= NULL,
	@direccion1			NVARCHAR(50),
	@direccion2			NVARCHAR(50)= NULL,
	@telefono		    NVARCHAR(50)= NULL,
	@celular			NVARCHAR(50),
	@email_principal	NVARCHAR(50),
	@email_secundario	NVARCHAR(50)= NULL,
	@usuario			NVARCHAR(50) = NULL,
	@passw				NVARCHAR(50) = NULL,
	@estado				INT,

	@id_CursoGuid		UNIQUEIDENTIFIER,
	@id_tutorGuid		UNIQUEIDENTIFIER
AS 
BEGIN
	DECLARE @PersonaGuid UNIQUEIDENTIFIER = NEWID()
	DECLARE @EstudianteGuid UNIQUEIDENTIFIER = NEWID()
	--DECLARE @CursoGuid UNIQUEIDENTIFIER

	INSERT INTO Persona (id_persona, nombre1, nombre2, apellido_paterno, apellido_materno,
				email1,emal2, fono, cel,dir1,dir2, usuario, pass, estado)

	VALUES	(@PersonaGuid, @primer_nombre, @segundo_nombre, @apellido_paterno, @apellido_materno, 
				@email_principal, @email_secundario, @telefono, @celular, @direccion1, @direccion2, @usuario, @passw, @estado)

    INSERT INTO Estudiante(id_estudiante,id_persona,id_curso,id_tutor)
	VALUES (@EstudianteGuid,@PersonaGuid,@id_CursoGuid, @id_tutorGuid)

	SELECT id_Estudiante
	FROM Estudiante
	WHERE id_estudiante = @EstudianteGuid

END




GO
/****** Object:  StoredProcedure [dbo].[Estudiante_GetByCourseId]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Estudiante_GetByCourseId] 
	-- Add the parameters for the stored procedure here
	@curso_GUID uniqueidentifier
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT est.id_estudiante, per.id_persona, per.nombre1, per.nombre2, per.apellido_paterno, per.apellido_materno,
			per.email1, per.emal2, per.fono, per.cel, per.dir1, per.dir2
	FROM Estudiante est, Persona per
	WHERE est.id_curso = @curso_GUID AND est.id_persona = per.id_persona
END
GO
/****** Object:  StoredProcedure [dbo].[EstudianteDelete]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[EstudianteDelete]

	@id_EstudienteGuid UNIQUEIDENTIFIER,
	@estado 			INT
AS 
BEGIN
	UPDATE Persona 
	SET estado = @estado
	FROM Persona per
		JOIN Estudiante est
			ON per.id_persona = est.id_persona AND est.id_estudiante = @id_EstudienteGuid
END

GO
/****** Object:  StoredProcedure [dbo].[EstudianteRead]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[EstudianteRead]

	@id_estudiante UNIQUEIDENTIFIER
AS 
BEGIN

	SELECT per.nombre1, per.nombre2, per.apellido_paterno, per.apellido_materno,per.dir1, 
			per.dir2, per.email1,per.emal2, per.fono,per.cel,per.usuario,per.estado
	FROM Persona per
		JOIN Estudiante est
		ON per.id_persona= est.id_persona AND est.id_estudiante = @id_estudiante
END

GO
/****** Object:  StoredProcedure [dbo].[EstudianteUpdate]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[EstudianteUpdate]

	@id_EstudianteGuid UNIQUEIDENTIFIER,
	
	@primer_nombre 		NVARCHAR(50),
	@segundo_nombre 	NVARCHAR(50) = NULL,
	@apellido_paterno 	NVARCHAR(50),
	@apellido_materno 	NVARCHAR(50)= NULL,
	@direccion1 		NVARCHAR(50),
	@direccion2 		NVARCHAR(50)= NULL,
	@telefono 			NVARCHAR(50)= NULL,
	@celular 			NVARCHAR(50),
	@email_principal 	NVARCHAR(50),
	@email_secundario 	NVARCHAR(50)= NULL
	
AS 
BEGIN
	UPDATE Persona 
	SET nombre1 = @primer_nombre ,nombre2 = @segundo_nombre, apellido_paterno = @apellido_paterno , apellido_materno = @apellido_materno,
	dir1 = @direccion1, dir2 = @direccion2, fono = @telefono, cel = @celular, email1 = @email_principal, emal2 = @email_secundario
	
	FROM Persona per
		JOIN Estudiante est
			ON per.id_persona = est.id_persona AND est.id_estudiante = @id_EstudianteGuid

END


GO
/****** Object:  StoredProcedure [dbo].[GetCursoGuid]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[GetCursoGuid]
	@curso NVARCHAR(50),
	@grado NVARCHAR(50)
AS 
BEGIN
	DECLARE @CursoGuid UNIQUEIDENTIFIER
	SET @CursoGuid = '00000000-0000-0000-0000-000000000000'

	SELECT @CursoGuid = id_curso
	FROM Curso
	WHERE nombre=@curso AND grado=@grado

	SELECT @CursoGuid
END


--select * from Curso where grado = '4' AND nombre='B'




GO
/****** Object:  StoredProcedure [dbo].[Login]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Login] 
	-- Add the parameters for the stored procedure here
	@usuario 			NVARCHAR(50),
	@passw 				NVARCHAR(50)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT id_persona FROM Persona WHERE usuario=@usuario AND pass=@passw
END
GO
/****** Object:  StoredProcedure [dbo].[Profesor_Create]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[Profesor_Create]
	@primer_nombre 		NVARCHAR(50),
	@segundo_nombre 	NVARCHAR(50) = NULL,
	@apellido_paterno 	NVARCHAR(50),
	@apellido_materno 	NVARCHAR(50)= NULL,
	@direccion1 		NVARCHAR(50),
	@direccion2 		NVARCHAR(50)= NULL,
	@telefono 			NVARCHAR(50)= NULL,
	@celular 			NVARCHAR(50),
	@email_principal 	NVARCHAR(50),
	@email_secundario 	NVARCHAR(50)= NULL,
	@usuario 			NVARCHAR(50),
	@passw 				NVARCHAR(50),
	@estado 			INT
AS
BEGIN
	DECLARE @PersonaGuid UNIQUEIDENTIFIER = NEWID()
	DECLARE @ProfesorGuid UNIQUEIDENTIFIER = NEWID()

	INSERT INTO Persona (id_persona, nombre1, nombre2, apellido_paterno, apellido_materno,
				email1,emal2, fono, cel,dir1,dir2, usuario, pass, estado)
	VALUES	(@PersonaGuid, @primer_nombre, @segundo_nombre, @apellido_paterno, @apellido_materno, 
				@email_principal, @email_secundario, @telefono, @celular, @direccion1, @direccion2, @usuario, @passw, @estado)

    INSERT INTO Profesor(id_profesor,id_persona)
	VALUES (@ProfesorGuid,@PersonaGuid)

	SELECT id_Profesor
	FROM Profesor
	WHERE id_profesor = @ProfesorGuid
END




GO
/****** Object:  StoredProcedure [dbo].[ProfesorDelete]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ProfesorDelete]

	@id_ProfesorGuid UNIQUEIDENTIFIER,
	@estado 			INT
AS 
BEGIN
	UPDATE Persona 
	SET estado = @estado
	FROM Persona per
		JOIN Profesor prof
			ON per.id_persona = prof.id_persona AND prof.id_profesor = @id_ProfesorGuid
END

GO
/****** Object:  StoredProcedure [dbo].[ProfesorRead]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ProfesorRead]

	@id_profesor UNIQUEIDENTIFIER
AS 
BEGIN

	SELECT per.nombre1, per.nombre2, per.apellido_paterno, per.apellido_materno,per.dir1, 
			per.dir2, per.email1,per.emal2, per.fono,per.cel,per.usuario,per.estado
	FROM Persona per
		JOIN Profesor prof
		ON per.id_persona= prof.id_persona AND prof.id_profesor = @id_profesor
END


GO
/****** Object:  StoredProcedure [dbo].[ProfesorUpdate]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ProfesorUpdate]

	@id_profesorGuid UNIQUEIDENTIFIER,

	@primer_nombre 		NVARCHAR(50),
	@segundo_nombre 	NVARCHAR(50) = NULL,
	@apellido_paterno 	NVARCHAR(50),
	@apellido_materno 	NVARCHAR(50)= NULL,
	@direccion1 		NVARCHAR(50),
	@direccion2 		NVARCHAR(50)= NULL,
	@telefono 			NVARCHAR(50)= NULL,
	@celular 			NVARCHAR(50),
	@email_principal 	NVARCHAR(50),
	@email_secundario 	NVARCHAR(50)= NULL

AS 
BEGIN
	UPDATE Persona 
	SET nombre1 = @primer_nombre ,nombre2 = @segundo_nombre, apellido_paterno = @apellido_paterno , apellido_materno = @apellido_materno,
	dir1 = @direccion1, dir2 = @direccion2, fono = @telefono, cel = @celular, email1 = @email_principal, emal2 = @email_secundario
	
	FROM Persona per
		JOIN Profesor prof
			ON per.id_persona = prof.id_persona AND prof.id_profesor = @id_profesorGuid 
END

GO
/****** Object:  StoredProcedure [dbo].[Tutor_Create]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[Tutor_Create]
	@primer_nombre 		NVARCHAR(50),
	@segundo_nombre 	NVARCHAR(50) = NULL,
	@apellido_paterno 	NVARCHAR(50),
	@apellido_materno 	NVARCHAR(50)= NULL,
	@direccion1 		NVARCHAR(50),
	@direccion2 		NVARCHAR(50)= NULL,
	@telefono 			NVARCHAR(50)= NULL,
	@celular 			NVARCHAR(50),
	@email_principal 	NVARCHAR(50),
	@email_secundario 	NVARCHAR(50)= NULL,
	@usuario 			NVARCHAR(50),
	@passw 				NVARCHAR(50),
	@estado 			INT
AS
BEGIN
	DECLARE @PersonaGuid UNIQUEIDENTIFIER = NEWID()
	DECLARE @TutorGuid UNIQUEIDENTIFIER = NEWID()

	INSERT INTO Persona (id_persona, nombre1, nombre2, apellido_paterno, apellido_materno,
				email1,emal2, fono, cel,dir1,dir2, usuario, pass, estado)
	VALUES	(@PersonaGuid, @primer_nombre, @segundo_nombre, @apellido_paterno, @apellido_materno, 
				@email_principal, @email_secundario, @telefono, @celular, @direccion1, @direccion2, @usuario, @passw, @estado)

    INSERT INTO Tutor(id_tutor,id_persona)
	VALUES (@TutorGuid,@PersonaGuid)

	SELECT id_tutor
	FROM Tutor
	WHERE id_tutor = @TutorGuid
END




GO
/****** Object:  StoredProcedure [dbo].[TutorDelete]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[TutorDelete]

	@id_tutorGuid UNIQUEIDENTIFIER,
	@estado 			INT
AS 
BEGIN
	UPDATE Persona 
	SET estado = @estado
	FROM Persona per
		JOIN Tutor tut
			ON per.id_persona = tut.id_persona AND tut.id_tutor = @id_tutorGuid
END

GO
/****** Object:  StoredProcedure [dbo].[TutorRead]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[TutorRead]

	@id_Tutor UNIQUEIDENTIFIER
AS 
BEGIN

	SELECT per.nombre1, per.nombre2, per.apellido_paterno, per.apellido_materno,per.dir1, 
			per.dir2, per.email1,per.emal2, per.fono,per.cel,per.usuario,per.estado
	FROM Persona per
		JOIN Tutor tut
		ON per.id_persona= tut.id_persona AND tut.id_Tutor = @id_Tutor
END

GO
/****** Object:  StoredProcedure [dbo].[TutorUpdate]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[TutorUpdate]

	@id_TutorGuid UNIQUEIDENTIFIER,

	@primer_nombre 		NVARCHAR(50),
	@segundo_nombre 	NVARCHAR(50) = NULL,
	@apellido_paterno 	NVARCHAR(50),
	@apellido_materno 	NVARCHAR(50)= NULL,
	@direccion1 		NVARCHAR(50),
	@direccion2 		NVARCHAR(50)= NULL,
	@telefono 			NVARCHAR(50)= NULL,
	@celular 			NVARCHAR(50),
	@email_principal 	NVARCHAR(50),
	@email_secundario 	NVARCHAR(50)= NULL

AS 
BEGIN
	UPDATE Persona 
	SET nombre1 = @primer_nombre ,nombre2 = @segundo_nombre, apellido_paterno = @apellido_paterno , apellido_materno = @apellido_materno,
	dir1 = @direccion1, dir2 = @direccion2, fono = @telefono, cel = @celular, email1 = @email_principal, emal2 = @email_secundario
	
	FROM Persona per
		JOIN Tutor tut
			ON per.id_persona = tut.id_persona AND tut.id_tutor = @id_TutorGuid 
END

GO
/****** Object:  Table [dbo].[Administrador]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Administrador](
	[id_administrador] [uniqueidentifier] NOT NULL,
	[cargo] [varchar](50) NOT NULL,
	[id_persona] [uniqueidentifier] NOT NULL,
 CONSTRAINT [PK_Administrador] PRIMARY KEY CLUSTERED 
(
	[id_administrador] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Asistencia]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Asistencia](
	[id_estudiante] [uniqueidentifier] NOT NULL,
	[id_tipo_asistencia] [uniqueidentifier] NOT NULL,
	[fecha] [date] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Curso]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Curso](
	[id_curso] [uniqueidentifier] NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[grado] [varchar](50) NOT NULL,
	[nivel] [int] NOT NULL,
 CONSTRAINT [PK_Curso] PRIMARY KEY CLUSTERED 
(
	[id_curso] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Disciplina]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Disciplina](
	[id_estudiante] [uniqueidentifier] NOT NULL,
	[id_profesor] [uniqueidentifier]  NULL,
	[id_administrador] [uniqueidentifier] NULL,
	[id_tipo_indisciplina] [uniqueidentifier] NOT NULL,
	[fecha_enviado] [date] NOT NULL,
	[mensaje_enviado] [varchar](200) NULL,
	[fecha_leido] [date] NOT NULL,
	[respuesta_padre] [varchar](200) NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Estudiante]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Estudiante](
	[id_estudiante] [uniqueidentifier] NOT NULL,
	[id_curso] [uniqueidentifier] NOT NULL,
	[id_persona] [uniqueidentifier] NOT NULL,
	[id_tutor] [uniqueidentifier] NOT NULL,
 CONSTRAINT [PK_Estudiante] PRIMARY KEY CLUSTERED 
(
	[id_estudiante] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Materia]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Materia](
	[nombre] [varchar](50) NOT NULL,
	[id_curso] [uniqueidentifier] NOT NULL,
	[id_profesor] [uniqueidentifier] NOT NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Persona]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Persona](
	[id_persona] [uniqueidentifier] NOT NULL,
	[nombre1] [varchar](50) NOT NULL,
	[nombre2] [varchar](50) NULL,
	[apellido_paterno] [varchar](50) NOT NULL,
	[apellido_materno] [varchar](50) NULL,
	[email1] [varchar](100) NOT NULL,
	[emal2] [varchar](100) NULL,
	[fono] [varchar](50) NULL,
	[cel] [varchar](50) NOT NULL,
	[dir1] [varchar](100) NOT NULL,
	[dir2] [varchar](100) NULL,
	[usuario] [varchar](50) NULL,
	[pass] [varchar](50) NULL,
	[estado] [int] NOT NULL,
 CONSTRAINT [PK_Persona] PRIMARY KEY CLUSTERED 
(
	[id_persona] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Profesor]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Profesor](
	[id_profesor] [uniqueidentifier] NOT NULL,
	[id_persona] [uniqueidentifier] NOT NULL,
 CONSTRAINT [PK_Profesor] PRIMARY KEY CLUSTERED 
(
	[id_profesor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Responsable]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Responsable](
	[id_tutor] [uniqueidentifier] NOT NULL,
	[id_estudiante] [uniqueidentifier] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tipoAsistencia]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tipoAsistencia](
	[id_tipo_asistencia] [uniqueidentifier] NOT NULL,
	[falta] [varchar](50) NULL,
	[retraso] [varchar](50) NULL,
	[justificacion] [varchar](50) NULL,
 CONSTRAINT [PK_tipoAsistencia] PRIMARY KEY CLUSTERED 
(
	[id_tipo_asistencia] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TipoIndisciplina]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TipoIndisciplina](
	[id_tipo_indisciplina] [uniqueidentifier] NOT NULL,
	[nombre] [varchar](50) NOT NULL,
 CONSTRAINT [PK_TipoIndisciplina] PRIMARY KEY CLUSTERED 
(
	[id_tipo_indisciplina] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Tutor]    Script Date: 23/03/2016 13:56:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tutor](
	[id_tutor] [uniqueidentifier] NOT NULL,
	[id_persona] [uniqueidentifier] NOT NULL,
 CONSTRAINT [PK_Tutor_1] PRIMARY KEY CLUSTERED 
(
	[id_tutor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Curso] ([id_curso], [nombre], [grado], [nivel]) VALUES (N'0ae4725c-a75b-4a01-b5a1-9da0ce51af24', N'''B''', N'''4''', 2)
INSERT [dbo].[Estudiante] ([id_estudiante], [id_curso], [id_persona], [id_tutor]) VALUES (N'167f5229-07f8-4476-a0d8-3031b6467316', N'0ae4725c-a75b-4a01-b5a1-9da0ce51af24', N'40ed6794-568d-422c-bcb3-e2f7d91b7768', N'5a94316a-37f0-4728-8ce5-d286d41515fd')
INSERT [dbo].[Persona] ([id_persona], [nombre1], [nombre2], [apellido_paterno], [apellido_materno], [email1], [emal2], [fono], [cel], [dir1], [dir2], [usuario], [pass], [estado]) VALUES (N'71313550-23ce-4599-a71f-0d2a653e3cdd', N'''Costo''', NULL, N'''Flores''', NULL, N'''dasdas@fmasm''', NULL, NULL, N'''55444555''', N'''calle locos''', NULL, N'''costososo''', N'''123''', 1)
INSERT [dbo].[Persona] ([id_persona], [nombre1], [nombre2], [apellido_paterno], [apellido_materno], [email1], [emal2], [fono], [cel], [dir1], [dir2], [usuario], [pass], [estado]) VALUES (N'64e97fed-3ff4-4215-a68a-7177198ce19c', N' Ricky', N'', N'Cardona', N'Silver', N'ronaldo@gmail.com', N'', N'454789236', N'701457836', N'calle Los Olivos # 478', N'', N'Tutor1', N'123', 1)
INSERT [dbo].[Persona] ([id_persona], [nombre1], [nombre2], [apellido_paterno], [apellido_materno], [email1], [emal2], [fono], [cel], [dir1], [dir2], [usuario], [pass], [estado]) VALUES (N'c7baf9e2-ef43-4247-aae4-99520bb969b1', N'''danny''', NULL, N'''flores''', NULL, N'''dabby@gsads''', NULL, NULL, N'''65211444''', N'''calle dos''', NULL, NULL, NULL, 1)
INSERT [dbo].[Persona] ([id_persona], [nombre1], [nombre2], [apellido_paterno], [apellido_materno], [email1], [emal2], [fono], [cel], [dir1], [dir2], [usuario], [pass], [estado]) VALUES (N'7e5cac2c-a028-4e33-bc94-ce343d2e7cf6', N'''dasdad''', NULL, N'''asasasa', NULL, N'''sadas@4@''', NULL, N'''13231''', N'''233323232''', N'''sadadas''', NULL, N'''12333''', N'''123''', 1)
INSERT [dbo].[Persona] ([id_persona], [nombre1], [nombre2], [apellido_paterno], [apellido_materno], [email1], [emal2], [fono], [cel], [dir1], [dir2], [usuario], [pass], [estado]) VALUES (N'40ed6794-568d-422c-bcb3-e2f7d91b7768', N'''Danny''', NULL, N'''Flores''', NULL, N'''danny2G@gmail.com''', NULL, NULL, N'''7062541457''', N'''Callo Rolas''', NULL, NULL, NULL, 1)
INSERT [dbo].[Persona] ([id_persona], [nombre1], [nombre2], [apellido_paterno], [apellido_materno], [email1], [emal2], [fono], [cel], [dir1], [dir2], [usuario], [pass], [estado]) VALUES (N'9531dcc0-6c1e-4869-b6b5-e52659e5404f', N'''COSTO''', NULL, N'''FLORES''', NULL, N'''DASDAS@GFDS''', N'''LOCS@GMAIL''', N'''134433443''', N'''604444445''', N'''CALLE UNO''', NULL, N'''danny''', N'''capo''', 1)
INSERT [dbo].[Persona] ([id_persona], [nombre1], [nombre2], [apellido_paterno], [apellido_materno], [email1], [emal2], [fono], [cel], [dir1], [dir2], [usuario], [pass], [estado]) VALUES (N'4375beda-84a0-4453-830b-fc4c47d83e6d', N' Ronaldo', N'', N'Cardona', N'Silver', N'ronaldo@gmail.com', N'', N'454789236', N'701457836', N'calle Los Olivos # 478', N'', N'Tutor1', N'123', 1)
INSERT [dbo].[Profesor] ([id_profesor], [id_persona]) VALUES (N'173b0123-e951-44a6-8ac5-8aea7258f801', N'7e5cac2c-a028-4e33-bc94-ce343d2e7cf6')
INSERT [dbo].[Profesor] ([id_profesor], [id_persona]) VALUES (N'1642e5f6-8d0e-4fd3-a983-e8e1f8ccd97e', N'71313550-23ce-4599-a71f-0d2a653e3cdd')
INSERT [dbo].[Tutor] ([id_tutor], [id_persona]) VALUES (N'17056c13-9b73-4340-b1bb-7f18fa49af10', N'64e97fed-3ff4-4215-a68a-7177198ce19c')
INSERT [dbo].[Tutor] ([id_tutor], [id_persona]) VALUES (N'2bf30a12-9da2-4ef7-afc3-8bae6adcd820', N'4375beda-84a0-4453-830b-fc4c47d83e6d')
INSERT [dbo].[Tutor] ([id_tutor], [id_persona]) VALUES (N'5a94316a-37f0-4728-8ce5-d286d41515fd', N'9531dcc0-6c1e-4869-b6b5-e52659e5404f')
ALTER TABLE [dbo].[Administrador]  WITH CHECK ADD  CONSTRAINT [FK_Administrador_Persona] FOREIGN KEY([id_persona])
REFERENCES [dbo].[Persona] ([id_persona])
GO
ALTER TABLE [dbo].[Administrador] CHECK CONSTRAINT [FK_Administrador_Persona]
GO
ALTER TABLE [dbo].[Asistencia]  WITH CHECK ADD  CONSTRAINT [FK_Asistencia_Estudiante] FOREIGN KEY([id_estudiante])
REFERENCES [dbo].[Estudiante] ([id_estudiante])
GO
ALTER TABLE [dbo].[Asistencia] CHECK CONSTRAINT [FK_Asistencia_Estudiante]
GO
ALTER TABLE [dbo].[Asistencia]  WITH CHECK ADD  CONSTRAINT [FK_Asistencia_tipoAsistencia] FOREIGN KEY([id_tipo_asistencia])
REFERENCES [dbo].[tipoAsistencia] ([id_tipo_asistencia])
GO
ALTER TABLE [dbo].[Asistencia] CHECK CONSTRAINT [FK_Asistencia_tipoAsistencia]
GO
ALTER TABLE [dbo].[Disciplina]  WITH CHECK ADD  CONSTRAINT [FK_Disciplina_Administrador] FOREIGN KEY([id_administrador])
REFERENCES [dbo].[Administrador] ([id_administrador])
GO
ALTER TABLE [dbo].[Disciplina] CHECK CONSTRAINT [FK_Disciplina_Administrador]
GO
ALTER TABLE [dbo].[Disciplina]  WITH CHECK ADD  CONSTRAINT [FK_Disciplina_Estudiante] FOREIGN KEY([id_estudiante])
REFERENCES [dbo].[Estudiante] ([id_estudiante])
GO
ALTER TABLE [dbo].[Disciplina] CHECK CONSTRAINT [FK_Disciplina_Estudiante]
GO
ALTER TABLE [dbo].[Disciplina]  WITH CHECK ADD  CONSTRAINT [FK_Disciplina_Profesor] FOREIGN KEY([id_profesor])
REFERENCES [dbo].[Profesor] ([id_profesor])
GO
ALTER TABLE [dbo].[Disciplina] CHECK CONSTRAINT [FK_Disciplina_Profesor]
GO
ALTER TABLE [dbo].[Disciplina]  WITH CHECK ADD  CONSTRAINT [FK_Disciplina_TipoIndisciplina] FOREIGN KEY([id_tipo_indisciplina])
REFERENCES [dbo].[TipoIndisciplina] ([id_tipo_indisciplina])
GO
ALTER TABLE [dbo].[Disciplina] CHECK CONSTRAINT [FK_Disciplina_TipoIndisciplina]
GO
ALTER TABLE [dbo].[Estudiante]  WITH CHECK ADD  CONSTRAINT [FK_Estudiante_Curso] FOREIGN KEY([id_curso])
REFERENCES [dbo].[Curso] ([id_curso])
GO
ALTER TABLE [dbo].[Estudiante] CHECK CONSTRAINT [FK_Estudiante_Curso]
GO
ALTER TABLE [dbo].[Materia]  WITH CHECK ADD  CONSTRAINT [FK_Materia_Curso] FOREIGN KEY([id_curso])
REFERENCES [dbo].[Curso] ([id_curso])
GO
ALTER TABLE [dbo].[Materia] CHECK CONSTRAINT [FK_Materia_Curso]
GO
ALTER TABLE [dbo].[Materia]  WITH CHECK ADD  CONSTRAINT [FK_Materia_Profesor] FOREIGN KEY([id_profesor])
REFERENCES [dbo].[Profesor] ([id_profesor])
GO
ALTER TABLE [dbo].[Materia] CHECK CONSTRAINT [FK_Materia_Profesor]
GO
ALTER TABLE [dbo].[Profesor]  WITH CHECK ADD  CONSTRAINT [FK_Profesor_Persona] FOREIGN KEY([id_persona])
REFERENCES [dbo].[Persona] ([id_persona])
GO
ALTER TABLE [dbo].[Profesor] CHECK CONSTRAINT [FK_Profesor_Persona]
GO
ALTER TABLE [dbo].[Responsable]  WITH CHECK ADD  CONSTRAINT [FK_Responsable_Estudiante] FOREIGN KEY([id_estudiante])
REFERENCES [dbo].[Estudiante] ([id_estudiante])
GO
ALTER TABLE [dbo].[Responsable] CHECK CONSTRAINT [FK_Responsable_Estudiante]
GO
ALTER TABLE [dbo].[Responsable]  WITH CHECK ADD  CONSTRAINT [FK_Responsable_Tutor] FOREIGN KEY([id_tutor])
REFERENCES [dbo].[Tutor] ([id_tutor])
GO
ALTER TABLE [dbo].[Responsable] CHECK CONSTRAINT [FK_Responsable_Tutor]
GO
ALTER TABLE [dbo].[Tutor]  WITH CHECK ADD  CONSTRAINT [FK_Tutor_Persona1] FOREIGN KEY([id_persona])
REFERENCES [dbo].[Persona] ([id_persona])
GO
ALTER TABLE [dbo].[Tutor] CHECK CONSTRAINT [FK_Tutor_Persona1]
GO
USE [master]
GO
ALTER DATABASE [school2] SET  READ_WRITE 
GO
