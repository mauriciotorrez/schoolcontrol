using Core.DTO;
using DAL;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ServerSchoolControl.Controllers
{
    public class ValuesController : ApiController
    {
        // GET api/values
        public IEnumerable<string> Get()
        {
            return new string[] { "value1", "value2" };
        }

//        // GET api/values/5
//        public string Get(int id)
//        {
//            //Message msgResult = new Message();
//            //KeyValuePairs rowResult = new KeyValuePairs();
//            Guid resull = Guid.Empty;
//            try
//            {






////ADMINISTRADOR
//                //Administrador tempAdministrador = new Administrador
//                //{
//                //    Nombre1 = "Marcelo",
//                //    Nombre2 = "",
//                //    Apellido_paterno = "Alvarez",
//                //    Apellido_materno = "Conani",
//                //    Mail1 = "Marcelo@gmail.com",
//                //    Mail2 = "",
//                //    fono = "454789236",
//                //    Cel = "701457836",
//                //    Dir1 = "calle Los Olivos # 478",
//                //    Dir2 = "",
//                //    Usuario = "ADmiN",
//                //    Passw = "123",
//                //    Estado = 1,
//                //    cargo = "ADMIns"
//                //};
//                //resull = DoCreateAdministrador(tempAdministrador);



////ESTUDIANTE
//                //Estudiante tempEstudiante = new Estudiante
//                //{
//                //    Nombre1 = "Alberto",
//                //    Nombre2 = "Ronal",
//                //    Apellido_paterno = "Claros",
//                //    Apellido_materno = "Rivera",
//                //    Mail1 = "alberto@gmail.com",
//                //    Mail2 = "",
//                //    fono = "4547896",
//                //    Cel = "70145786",
//                //    Dir1 = "calle Los Pinos # 478",
//                //    Dir2 = "",
//                //    Usuario = "",
//                //    Passw = "",
//                //    Estado = 1
//                //};
//                //resull = DoCreateEstudiante(tempEstudiante, "B", "4");



//            }
//            catch (Exception ex)
//            {
//                //resull = "error";
//            }
//            return resull.ToString();
//        }

         //POST api/values
        //[HttpPost]
        //[ActionName("test")]
        //public void Post([FromBody]object value)
        //{
        //}

        // PUT api/values/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE api/values/5
        public void Delete(int id)
        {
        }

        //[HttpPost]
        ////[ActionName("test")]
        //public Message test([FromBody]Message t)
        //{
        //    return new Message();
        //}
        
        //[HttpPost]
        [ActionName("Save")]
        public Message Save(Message value)
        {
            //object value1 = new object();
            //Message value = value1 as Message;
            Message result = null;
            if (value != null && value is Message && (value as Message).Header != null && (value as Message).Body != null)
            {
//ESTUDIANTE-------------------
                //Estudiante tempEstudiante = new Estudiante
                //{
                //    Nombre1 = "Alberto",
                //    Nombre2 = "Ronal",
                //    Apellido_paterno = "Claros",
                //    Apellido_materno = "Rivera",
                //    Mail1 = "alberto@gmail.com",
                //    Mail2 = "",
                //    fono = "4547896",
                //    Cel = "70145786",
                //    Dir1 = "calle Los Pinos # 478",
                //    Dir2 = "",
                //    Usuario = "",
                //    Passw = "",
                //    Estado = 1,
                //    CursoNombre = "B",
                //    GradoCurso = "4"
                //};
                //Message test = new Message();
                //test.Header = new MsgHeader { MessageType = "CreateEstudiante" };
                //test.Body = new MsgBody
                //{
                //    Key = "Estudiante",
                //    Value = tempEstudiante
                //};
                //value = test;



////PROFESOR
//                Profesor tempProfesor = new Profesor
//                {
//                    Nombre1 = "Marcelo",
//                    Nombre2 = "",
//                    Apellido_paterno = "Alvarez",
//                    Apellido_materno = "Conani",
//                    Mail1 = "Marcelo@gmail.com",
//                    Mail2 = "",
//                    fono = "454789236",
//                    Cel = "701457836",
//                    Dir1 = "calle Los Olivos # 478",
//                    Dir2 = "",
//                    Usuario = "ADmiN",
//                    Passw = "123",
//                    Estado = 1

//                };

//                Message test = new Message();
//                test.Header = new MsgHeader { MessageType = "CreateProfesor" };
//                test.Body = new MsgBody
//                {
//                    Key = "Profesor",
//                    Value = tempProfesor
//                };
//                value = test;



//ADMINISTRADOR----------------------
                //Administrador tempAdministrador = new Administrador
                //{
                //    Nombre1 = "RObeRTTTTTTTOOOTO",
                //    Nombre2 = "chileno",
                //    Apellido_paterno = "GOnzales",
                //    Apellido_materno = "Koanano",
                //    Mail1 = "Roberto@gmail.com",
                //    Mail2 = "",
                //    fono = "454789236",
                //    Cel = "701457836",
                //    Dir1 = "calle Los Olivos # 478",
                //    Dir2 = "",
                //    Usuario = "ADmiN",
                //    Passw = "123",
                //    Estado = 1,
                //    cargo = "ADMIns"
                //};
                //Message test = new Message();
                //test.Header = new MsgHeader { MessageType = "CreateAdministador" };
                //test.Body = new MsgBody
                //{
                //    Key = "Administrador",
                //    Value = tempAdministrador
                //};
                //value = test;



//ADMINISTRADOR----------------------
                //Administrador tempAdministrador = new Administrador
                //{
                //    Nombre1 = "RObeRTTTTTTTOOOTO",
                //    Nombre2 = "chileno",
                //    Apellido_paterno = "GOnzales",
                //    Apellido_materno = "Koanano",
                //    Mail1 = "Roberto@gmail.com",
                //    Mail2 = "",
                //    fono = "454789236",
                //    Cel = "701457836",
                //    Dir1 = "calle Los Olivos # 478",
                //    Dir2 = "",
                //    Usuario = "ADmiN",
                //    Passw = "123",
                //    Estado = 1,
                //    cargo = "ADMIns"
                //};
                //Message test = new Message();
                //test.Header = new MsgHeader { MessageType = "CreateAdministador" };
                //test.Body = new MsgBody
                //{
                //    Key = "Administrador",
                //    Value = tempAdministrador
                //};
                //value = test;



//TUTOR
                //Tutor tempTutor = new Tutor
                //{
                //    Nombre1 = " Ricky",
                //    Nombre2 = "",
                //    Apellido_paterno = "Cardona",
                //    Apellido_materno = "Silver",
                //    Mail1 = "ronaldo@gmail.com",
                //    Mail2 = "",
                //    fono = "454789236",
                //    Cel = "701457836",
                //    Dir1 = "calle Los Olivos # 478",
                //    Dir2 = "",
                //    Usuario = "Tutor1",
                //    Passw = "123",
                //    Estado = 1

                //};

                //Message test = new Message();
                //test.Header = new MsgHeader { MessageType = "CreateTutor" };
                //test.Body = new MsgBody
                //{
                //    Key = "Tutor",
                //    Value = tempTutor
                //};
                //value = test;

                //-------------------

                Workers worker = new Workers();
                string messageType = value.Header.MessageType;

                switch (messageType)
                {
                    case "CreateEstudiante":
                        result = worker.DoCreateEstudiante(value);//terminar
                        break;

                    case "CreateProfesor":
                        result = worker.DoCreateProfesor(new Profesor());//revisar
                        break;

                    case "CreateAdministador":
                        result = worker.DoCreateAdministrador(new Administrador());//revisar
                        break;

                    case "CreateTutor":
                        result = worker.DoCreateTutor(new Tutor());//revisar
                        break;
                    case "Login":
                        result = worker.DoLogin(value);
                        break;
                    case "CoursesGet":
                        result = worker.DoCoursesGet();
                        break;
                    case "EstudiantesGetByCourseId":
                        result = worker.DoEstudiantesGetByCourseId(value);
                        break;
                    case "SaveFault":
                        result = worker.DoSaveFault(value);
                        break;
                    case "SaveAsistencia":
                        result = worker.DoSaveAsistencia(value);
                        break;
                    case "FaultGet":
                        result = worker.DoFaultGetByUserID(value);
                        break;
                    case "PendingFaults":
                        result = worker.DoPendingFaults(value);
                        break;
                    case "FaultRead":
                        result = worker.DoFaultRead(value);
                        break;
                    default:
                        break;
                }
            }
            return result;
        }
        

//        //TODO change string input parameters to PERSONA
//        public int DoCreatePersona(string nom1, string nom2, string apelli, string mail1, string direc, string cel)
//        {
//            //Message msgResult = new Message();
//            //KeyValuePairs rowResult = new KeyValuePairs();
//            int createdPersonaId = 0;
//            try
//            {
//                CreatePersona cpDA = new CreatePersona();
//                if (!string.IsNullOrEmpty(nom1) && !string.IsNullOrEmpty(nom1) &&
//                    !string.IsNullOrEmpty(apelli) && !string.IsNullOrEmpty(mail1) &&
//                    !string.IsNullOrEmpty(direc) && !string.IsNullOrEmpty(cel))
//                {
//                    Persona tempPersona = new Persona
//                    {
//                        Nombre1 = nom1,
//                        Nombre2 = nom2,
//                        Apellido = apelli,
//                        Mail1 = mail1,
//                        Dir1 = direc,
//                        Cel = cel
//                    };

//                    createdPersonaId = cpDA.CreatePersonaDB(tempPersona);
//                }
//                else
//                {
//                    throw new Exception("CacahuateException('Invalid Latitude,  Longitude')");
//                }

//                //msgResult.Header.MessageType = "Login";
//                //rowResult.Add(new StringObjectPair { Key = "Logged", Value = loggedGuid });
//            }
//            //catch (CacahuateException c)
//            //{
//            //    rowResult.Add(new StringObjectPair { Key = "CacahuateException", Value = c.Message });
//            //}
//            //catch (Exception ex)
//            //{
//            //    rowResult.Add(new StringObjectPair { Key = "Exception", Value = ex.Message });
//            //}
//            //msgResult.Body.Add(rowResult);
//            //msgResult.Header.Anchor = 0;
//            catch (Exception ex)
//            {
//                createdPersonaId = 0;
//            }
//            return createdPersonaId;
//        }








////ADMINISTRADOR
//        public Guid DoCreateAdministrador(Administrador tempAdministrador)
//        {
//            Guid createdPersonaId;
//            try
//            {
//                CreateAdministador caDA = new CreateAdministador();
//                if (!string.IsNullOrEmpty(tempAdministrador.Nombre1) && !string.IsNullOrEmpty(tempAdministrador.Apellido_paterno) &&
//                    !string.IsNullOrEmpty(tempAdministrador.Mail1)   && !string.IsNullOrEmpty(tempAdministrador.Cel) &&
//                    !string.IsNullOrEmpty(tempAdministrador.Dir1)    && !String.IsNullOrEmpty(tempAdministrador.cargo))
//                {
//                    createdPersonaId = caDA.CreateAdministradorDB(tempAdministrador);
//                }
//                else
//                {
//                    throw new Exception("CacahuateException('Invalid Latitude, Longitude')");
//                }
//            }

//            catch (Exception ex)
//            {
//                createdPersonaId = Guid.Empty;
//            }
//            return createdPersonaId;
//        }


        
    }
}