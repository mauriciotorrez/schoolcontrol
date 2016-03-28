using Core.DTO;
using DAL;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ServerSchoolControl.Controllers
{
    public class Workers
    {
//ESTUDIANTE
        public Message DoCreateEstudiante(Estudiante tempEstudiante)
        {
            Message msgResult = new Message();
            Guid createdPersonaId;
            try
            {
                CreateEstudiante ceDA = new CreateEstudiante();
                if (!string.IsNullOrEmpty(tempEstudiante.Nombre1) && !string.IsNullOrEmpty(tempEstudiante.Apellido_paterno) &&
                    !string.IsNullOrEmpty(tempEstudiante.Mail1) && !string.IsNullOrEmpty(tempEstudiante.Cel) &&
                    !string.IsNullOrEmpty(tempEstudiante.Dir1))
                {
                    createdPersonaId = ceDA.CreateEstudianteDB(tempEstudiante, tempEstudiante.CursoNombre, tempEstudiante.GradoCurso);
                }
                else
                {
                    throw new Exception("CacahuateException('Invalid Latitude, Longitude')");
                }
            }

            catch (Exception ex)
            {
                createdPersonaId = Guid.Empty;
            }

            msgResult.Body = new MsgBody
            {
                Key = "GuidEstudiante",
                Value = createdPersonaId
            };

            return msgResult;
        }

//PROFESOR
        public Message DoCreateProfesor(Profesor tempProfesor)
        {
            Message msgResult = new Message();
            Guid createdProfesorId;
            try
            {
                CreateProfesor cprofDA = new CreateProfesor();
                if (!string.IsNullOrEmpty(tempProfesor.Nombre1) && !string.IsNullOrEmpty(tempProfesor.Apellido_paterno) &&
                    !string.IsNullOrEmpty(tempProfesor.Mail1) && !string.IsNullOrEmpty(tempProfesor.Cel) &&
                    !string.IsNullOrEmpty(tempProfesor.Dir1))
                {
                    createdProfesorId = cprofDA.CreateProfesorDB(tempProfesor);
                }
                else
                {
                    throw new Exception("CacahuateException('Invalid Latitude,  Longitude')");
                }
            }
            catch (Exception ex)
            {
                createdProfesorId = Guid.Empty;
            }

            msgResult.Body = new MsgBody
            {
                Key = "GuidProfesor",
                Value = createdProfesorId
            };
            return msgResult;
        }
//ADMINISTRADOR
        public Message DoCreateAdministrador(Administrador tempAdministrador)
        {
            Message msgResult = new Message();
            Guid createdAdministradorId;
            try
            {
                CreateAdministador caDA = new CreateAdministador();
                if (!string.IsNullOrEmpty(tempAdministrador.Nombre1) && !string.IsNullOrEmpty(tempAdministrador.Apellido_paterno) &&
                    !string.IsNullOrEmpty(tempAdministrador.Mail1) && !string.IsNullOrEmpty(tempAdministrador.Cel) &&
                    !string.IsNullOrEmpty(tempAdministrador.Dir1) && !String.IsNullOrEmpty(tempAdministrador.cargo))
                {
                    createdAdministradorId = caDA.CreateAdministradorDB(tempAdministrador);
                }
                else
                {
                    throw new Exception("CacahuateException('Invalid Latitude, Longitude')");
                }
            }

            catch (Exception ex)
            {
                createdAdministradorId = Guid.Empty;
            }
            msgResult.Body = new MsgBody
            {
                Key = "GuidAdministrador",
                Value = createdAdministradorId
            };
            return msgResult;
        }
//TUTOR
        public Message DoCreateTutor(Tutor tempTutor)
        {
            Message msgResult = new Message();
            Guid createdTutorId;
            try
            {
                CreateTutor ctutDA = new CreateTutor();
                if (!string.IsNullOrEmpty(tempTutor.Nombre1)    && !string.IsNullOrEmpty(tempTutor.Apellido_paterno) &&
                    !string.IsNullOrEmpty(tempTutor.Mail1)      && !string.IsNullOrEmpty(tempTutor.Cel)              &&
                    !string.IsNullOrEmpty(tempTutor.Dir1))
                {
                    createdTutorId = ctutDA.CreateTutorDB(tempTutor);
                }
                else
                {
                    throw new Exception("CacahuateException('Invalid Latitude,  Longitude')");
                }
            }
            catch (Exception ex)
            {
                createdTutorId = Guid.Empty;
            }

            msgResult.Body = new MsgBody
            {
                Key = "GuidProfesor",
                Value = createdTutorId
            };
            return msgResult;
        }



        public Message DoLogin(User user)
        {
            Message msgResult = new Message();
            Guid userLogedId;
            try
            {
                LoginDA loginDA = new LoginDA();
                if (!string.IsNullOrEmpty(user.Usuario) && !string.IsNullOrEmpty(user.Passw))
                {
                    userLogedId = loginDA.Login(user);
                }
                else
                {
                    throw new Exception("CacahuateException('Invalid Latitude,  Longitude')");
                }
            }
            catch (Exception ex)
            {
                userLogedId = Guid.Empty;
            }
            msgResult.Body = new MsgBody
            {
                Key = "GuidUser",
                Value = userLogedId
            };
            return msgResult;
        }

        public Message DoCoursesGet()
        {
            Message msgResult = new Message();
            List<Curso> cursos = new List<Curso>();
            try
            {
                CursoDA cursoDA = new CursoDA();
                cursos = cursoDA.GetCourses();
            }
            catch (Exception ex)
            {
                //userLogedId = Guid.Empty;
            }
            msgResult.Body = new MsgBody
            {
                Key = "Courses",
                Value = cursos
            };
            return msgResult;
        }

        public Message DoEstudiantesGetByCourseId(Guid cursoId)
        {
            Message msgResult = new Message();
            List<Estudiante> estudiantes = new List<Estudiante>();
            try
            {
                CreateEstudiante estudianteDA = new CreateEstudiante();
                estudiantes = estudianteDA.Estudiante_GetByCourseId(cursoId);
            }
            catch (Exception ex)
            {
                //userLogedId = Guid.Empty;
            }
            msgResult.Body = new MsgBody
            {
                Key = "Estudiantes",
                Value = estudiantes
            };
            return msgResult;
        }



        public Message DoSaveFault(Fault fault)
        {
            Message msgResult = new Message();
            Guid faultId = Guid.Empty;
            try
            {
                FaultDA faultDA = new FaultDA();
                faultId = faultDA.SaveFault(fault);
            }
            catch (Exception ex)
            {
                //userLogedId = Guid.Empty;
            }
            msgResult.Body = new MsgBody
            {
                Key = "Fault",
                Value = faultId
            };
            return msgResult;
        }

        public Message DoSaveAsistencia(Asistencia asistencia)
        {
            Message msgResult = new Message();
            Guid asistenciatId = Guid.Empty;
            try
            {
                AsistenciaDA asistenciaDA = new AsistenciaDA();
                asistenciatId = asistenciaDA.SaveAsistencia(asistencia);
            }
            catch (Exception ex)
            {
                //userLogedId = Guid.Empty;
            }
            msgResult.Body = new MsgBody
            {
                Key = "Fault",
                Value = asistenciatId
            };
            return msgResult;
        }
    }
}