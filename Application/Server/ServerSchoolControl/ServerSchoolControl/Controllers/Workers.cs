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
        public Message DoCreateEstudiante(Message value)
        {
            Estudiante tempEstudiante;
            Message msgResult = new Message();
            KeyValuePairs rowResult = new KeyValuePairs();
            Guid createdPersonaId;

            string nombre1 = string.Empty;
            string nombre2 = string.Empty;
            string apellido_paterno = string.Empty;

            try
            {                
                foreach (KeyValuePairs valuePair in value.Body)
                {
                    foreach (StringObjectPair stringPair in valuePair)
                    {
                        if (String.Equals(stringPair.Key, "Nombre1", StringComparison.InvariantCultureIgnoreCase) && stringPair.Value != null)
                        {
                            nombre1 = stringPair.Value.ToString();
                        }
                        if (String.Equals(stringPair.Key, "Nombre2", StringComparison.InvariantCultureIgnoreCase) && stringPair.Value != null)
                        {
                            nombre2 = stringPair.Value.ToString();
                        }
                        if (String.Equals(stringPair.Key, "Apellido_paterno", StringComparison.InvariantCultureIgnoreCase) && stringPair.Value != null)
                        {
                            apellido_paterno = stringPair.Value.ToString();
                        } 
                      
                        // completar todos los valores q faltan

                    }
                }

                //crear estudiante DTO

                tempEstudiante = new Estudiante
                {
                    Nombre1 = nombre1,
                    Nombre2 = string.IsNullOrEmpty(nombre2) ? null : nombre2,
                    Apellido_paterno = apellido_paterno,
                    //hacer lo de abajo no ejecutar.. completar
                    Apellido_materno = "Rivera",
                    Mail1 = "alberto@gmail.com",
                    Mail2 = "",
                    fono = "4547896",
                    Cel = "70145786",
                    Dir1 = "calle Los Pinos # 478",
                    Dir2 = "",
                    Usuario = "",
                    Passw = "",
                    Estado = 1,
                    CursoNombre = "B",
                    GradoCurso = "4"
                };
                //revisar login para el orden de la validacion
                if (string.IsNullOrEmpty(tempEstudiante.Nombre1) || string.IsNullOrEmpty(tempEstudiante.Apellido_paterno) ||
                    string.IsNullOrEmpty(tempEstudiante.Mail1) || string.IsNullOrEmpty(tempEstudiante.Cel) ||
                    string.IsNullOrEmpty(tempEstudiante.Dir1))
                {
                    throw new Exception("Error param Nombre1 ......");
                }

                CreateEstudiante ceDA = new CreateEstudiante();
                createdPersonaId = ceDA.CreateEstudianteDB(tempEstudiante, tempEstudiante.CursoNombre, tempEstudiante.GradoCurso);
                rowResult.Add(new StringObjectPair { Key = "GuidEstudiante", Value = createdPersonaId });
            }
            catch (Exception ex)
            {
                rowResult.Add(new StringObjectPair { Key = "GuidEstudianteException", Value = ex.Message });
            }
            msgResult.Body.Add(rowResult);
           return msgResult;
        }

//PROFESOR
        public Message DoCreateProfesor(Profesor tempProfesor)
        {
            
            Message msgResult = new Message();
            KeyValuePairs rowResult = new KeyValuePairs();
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
            rowResult.Add(new StringObjectPair { Key = "GuidProfesor", Value = createdProfesorId });
            msgResult.Body.Add(rowResult);
            return msgResult;
        }
//ADMINISTRADOR
        public Message DoCreateAdministrador(Administrador tempAdministrador)
        {
            Message msgResult = new Message();
            KeyValuePairs rowResult = new KeyValuePairs();
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
                    throw new Exception("CacahuateException('Invalid Latitude, Longitude'");
                }
            }
            catch (Exception ex)
            {
                createdAdministradorId = Guid.Empty;
            }
            rowResult.Add(new StringObjectPair { Key = "GuidAdministrador", Value = createdAdministradorId });
            msgResult.Body.Add(rowResult);
            return msgResult;
        }
//TUTOR
        public Message DoCreateTutor(Tutor tempTutor)
        {
            Message msgResult = new Message();
            KeyValuePairs rowResult = new KeyValuePairs();
            Guid createdTutorId;
            try
            {
                if (tempTutor == null)
                {
                    throw new Exception("NULL Param Tutor tempTutor");
                }

                if (string.IsNullOrEmpty(tempTutor.Nombre1) || string.IsNullOrEmpty(tempTutor.Apellido_paterno) ||
                    string.IsNullOrEmpty(tempTutor.Mail1) || string.IsNullOrEmpty(tempTutor.Cel) ||
                    string.IsNullOrEmpty(tempTutor.Dir1))
                {
                    throw new Exception("INVALID Nombre1, Apellido_paterno, Mail1, Cel, Dir1");
                }

                CreateTutor ctutDA = new CreateTutor();
                createdTutorId = ctutDA.CreateTutorDB(tempTutor);
                rowResult.Add(new StringObjectPair { Key = "GuidProfesor", Value = createdTutorId });
            }
            catch (Exception ex)
            {
                rowResult.Add(new StringObjectPair { Key = "GuidProfesorError", Value = ex.Message });
            }
            msgResult.Body.Add(rowResult);
            return msgResult;
        }



        public Message DoLogin(Message value)
        {            
            Message msgResult = new Message();
            KeyValuePairs rowResult = new KeyValuePairs();
            msgResult.Header.MessageType = "Login";
            Guid userLogedId;
            string username = string.Empty;
            string pass = string.Empty;
            try
            {
                foreach (KeyValuePairs valuePair in value.Body)
                {
                    foreach (StringObjectPair stringPair in valuePair)
                    {
                        if (String.Equals(stringPair.Key, "Usuario", StringComparison.InvariantCultureIgnoreCase) && stringPair.Value != null)
                        {
                            username = stringPair.Value.ToString();
                        }
                        if (String.Equals(stringPair.Key, "Passw", StringComparison.InvariantCultureIgnoreCase) && stringPair.Value != null)
                        {
                            pass = stringPair.Value.ToString();
                        }
                    }
                }

                if (string.IsNullOrEmpty(username) || string.IsNullOrEmpty(pass))
                {
                    throw new Exception("Invalid Usuario,  Passw");
                }

                User user = new User{
                    Usuario = username,
                    Passw = pass
                };

                LoginDA loginDA = new LoginDA();
                userLogedId = loginDA.Login(user);
                rowResult.Add(new StringObjectPair { Key = "GuidUser", Value = userLogedId });
            }
            catch (Exception ex)
            {
                rowResult.Add(new StringObjectPair { Key = "GuidUserError", Value = ex.Message });
            }
            msgResult.Body.Add(rowResult);
            return msgResult;
        }

        public Message DoCoursesGet()
        {
            Message msgResult = new Message();
            KeyValuePairs rowResult = new KeyValuePairs();
            msgResult.Header.MessageType = "CourseGet";
            List<Curso> cursos = new List<Curso>();
            try
            {
                CursoDA cursoDA = new CursoDA();
                cursos = cursoDA.GetCourses();
                rowResult.Add(new StringObjectPair { Key = "Courses", Value = cursos });
            }
            catch (Exception ex)
            {
                rowResult.Add(new StringObjectPair { Key = "CoursesGetError", Value = ex.Message });
            }
            msgResult.Body.Add(rowResult);
            return msgResult;
        }

        public Message DoEstudiantesGetByCourseId(Message value)
        {
            Guid cursoId = Guid.Empty;
            Message msgResult = new Message();
            msgResult.Header.MessageType = "EstudiantesGetByCourseId";
            KeyValuePairs rowResult = new KeyValuePairs();
            List<Estudiante> estudiantes = new List<Estudiante>();
            try
            {
                foreach (KeyValuePairs valuePair in value.Body)
                {
                    foreach (StringObjectPair stringPair in valuePair)
                    {
                        if (String.Equals(stringPair.Key, "GuidCurso", StringComparison.InvariantCultureIgnoreCase) && stringPair.Value != null)
                        {
                            cursoId = Guid.Parse(stringPair.Value.ToString());
                        }
                    }
                }

                if (cursoId == Guid.Empty)
                {
                    throw new Exception("Course Guid empty");
                }

                CreateEstudiante estudianteDA = new CreateEstudiante();
                estudiantes = estudianteDA.Estudiante_GetByCourseId(cursoId);
                rowResult.Add(new StringObjectPair { Key = "Estudiantes", Value = estudiantes });
            }
            catch (Exception ex)
            {
                rowResult.Add(new StringObjectPair { Key = "EstudiantesError", Value = ex.Message });
            }
            msgResult.Body.Add(rowResult);
            return msgResult;
        }



        public Message DoSaveFault(Message value)
        {
            Fault fault;
            Message msgResult = new Message();
            msgResult.Header.MessageType = "SaveFault";
            KeyValuePairs rowResult = new KeyValuePairs();
            Guid faultId = Guid.Empty;

            Guid guidEstudiante = Guid.Empty;
            Guid guidProfesor = Guid.Empty;
            Guid guidAdministrador = Guid.Empty;
            Guid guidTypoFault = Guid.Empty;
            DateTime fechaEnviado = DateTime.Now;
            String mensaje = string.Empty;
            String repuesta = string.Empty;

            try
            {
                foreach (KeyValuePairs valuePair in value.Body)
                {
                    foreach (StringObjectPair stringPair in valuePair)
                    {
                        if (String.Equals(stringPair.Key, "GuidEstudiante", StringComparison.InvariantCultureIgnoreCase) && stringPair.Value != null)
                        {
                            guidEstudiante = Guid.Parse(stringPair.Value.ToString());
                        }
                        if (String.Equals(stringPair.Key, "GuidProfesor", StringComparison.InvariantCultureIgnoreCase) && stringPair.Value != null)
                        {
                            guidProfesor = Guid.Parse(stringPair.Value.ToString());
                        }
                        if (String.Equals(stringPair.Key, "GuidAdministrador", StringComparison.InvariantCultureIgnoreCase) && stringPair.Value != null)
                        {
                            guidAdministrador = Guid.Parse(stringPair.Value.ToString());
                        }
                        if (String.Equals(stringPair.Key, "GuidTypoFault", StringComparison.InvariantCultureIgnoreCase) && stringPair.Value != null)
                        {
                            guidTypoFault = Guid.Parse(stringPair.Value.ToString());
                        }
                        if (String.Equals(stringPair.Key, "Mensage", StringComparison.InvariantCultureIgnoreCase) && stringPair.Value != null)
                        {
                            mensaje = stringPair.Value.ToString();
                        }
                        if (String.Equals(stringPair.Key, "Repuesta", StringComparison.InvariantCultureIgnoreCase) && stringPair.Value != null)
                        {
                            repuesta = stringPair.Value.ToString();
                        }
                    }
                }

                if (guidEstudiante == Guid.Empty || guidTypoFault == Guid.Empty)
                {
                    throw new Exception("Error creating guidEstudiante or guidTypoFault");
                }

                fault = new Fault
                {
                    GuidEstudiante = guidEstudiante,
                    GuidProfesor = guidProfesor,
                    GuidAdministrador = guidAdministrador,
                    GuidTypoFault = guidTypoFault,
                    FechaEnviado = fechaEnviado,
                    Mensage = mensaje
                };

                FaultDA faultDA = new FaultDA();
                faultId = faultDA.SaveFault(fault);
                rowResult.Add(new StringObjectPair { Key = "Fault", Value = faultId });
            }
            catch (Exception ex)
            {
                rowResult.Add(new StringObjectPair { Key = "FaultError", Value = ex.Message });
            }
            msgResult.Body.Add(rowResult);
            return msgResult;
        }

        public Message DoSaveAsistencia(Message value)
        {
            Asistencia asistencia;
            Message msgResult = new Message();
            msgResult.Header.MessageType = "SaveAsistencia";
            KeyValuePairs rowResult = new KeyValuePairs();
            Guid asistenciatId = Guid.Empty;

            Guid guidEstudiante = Guid.Empty;
            DateTime fecha = DateTime.Now;
            int? falta = null;
            int? retrazo = null;
            int? justificacion = null;
            int tempNumber = 0;

            try
            {

                foreach (KeyValuePairs valuePair in value.Body)
                {
                    foreach (StringObjectPair stringPair in valuePair)
                    {
                        if (String.Equals(stringPair.Key, "GuidEstudiante", StringComparison.InvariantCultureIgnoreCase) && stringPair.Value != null)
                        {
                            guidEstudiante = Guid.Parse(stringPair.Value.ToString());
                        }
                        if (String.Equals(stringPair.Key, "Falta", StringComparison.InvariantCultureIgnoreCase) && stringPair.Value != null &&
                            Int32.TryParse(stringPair.Value.ToString(), out tempNumber))
                        {
                            falta = tempNumber;
                        }
                        if (String.Equals(stringPair.Key, "Retrazo", StringComparison.InvariantCultureIgnoreCase) && stringPair.Value != null &&
                            Int32.TryParse(stringPair.Value.ToString(), out tempNumber))
                        {
                            retrazo = tempNumber;
                        }
                        if (String.Equals(stringPair.Key, "Justificacion", StringComparison.InvariantCultureIgnoreCase) && stringPair.Value != null &&
                            Int32.TryParse(stringPair.Value.ToString(), out tempNumber))
                        {
                            justificacion = tempNumber;
                        }
                    }
                }

                if (guidEstudiante == Guid.Empty)
                {
                    throw new Exception("Error creating guidEstudiante ");
                }

                asistencia = new Asistencia
                {
                    GuidEstudiante = guidEstudiante,
                    Fecha = fecha,
                    Falta = falta,
                    Retrazo = retrazo,
                    Justificacion = justificacion
                };

                AsistenciaDA asistenciaDA = new AsistenciaDA();
                asistenciatId = asistenciaDA.SaveAsistencia(asistencia);
                rowResult.Add(new StringObjectPair { Key = "Asistencia", Value = asistenciatId });
            }
            catch (Exception ex)
            {
                rowResult.Add(new StringObjectPair { Key = "AsistenciaError", Value = ex.Message });
            }
            msgResult.Body.Add(rowResult);
            return msgResult;
        }
    }
}