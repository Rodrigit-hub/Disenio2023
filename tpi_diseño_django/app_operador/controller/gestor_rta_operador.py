# controller/gestor_rta_operador.py
from ..models import Estado, Llamada, CategoriaLlamada, Validacion, Cliente
from django.utils import timezone
# Accion?

# Métodos relacionados con CambioEstado


class GestorRtaOperador:
    llamadaEnCurso = None

    def nuevaRespuestaOperador(self):
        self.recibirLlamada()

    def recibirLlamada(self, llamada):
        self.setLlamadaEnCurso(llamada)
        fechaHoraActual = self.obtenerFechaHoraActual()
        estadoEnCurso = self.buscarEstadoEnCurso()
        self.llamadaEnCurso.tomadaPorOperador(
            fechaHoraActual,
            estadoEnCurso
        )

    def finCU(self):
        pass

    def obtenerDatosLlamada(self):
        cliente = self.llamadaEnCurso.cliente.getNombre()
        return cliente

    def finalizarLlamada(self):
        fechaHoraActual = self.obtenerFechaHoraActual()
        estadoFinalizada = self.buscarEstadoFinalizada()
        self.llamadaEnCurso.finalizarLlamada(fechaHoraActual, estadoFinalizada)

    def buscarEstadoEnCurso(self):
        estados = Estado.objects.all()
        estadoEnCurso = next(
            (estado for estado in estados if estado.esEnCurso()), None)
        return estadoEnCurso

    def buscarEstadoFinalizada(self):
        estados = Estado.objects.all()
        estadoFinalizada = next(
            (estado for estado in estados if estado.esFinalizada()), None)
        return estadoFinalizada

    def obtenerFechaHoraActual(self):
        fechaHoraActual = timezone.now()
        return fechaHoraActual

    def getLlamadaEnCurso(self) -> Llamada:
        return self.llamadaEnCurso

    def setLlamadaEnCurso(self, llamada):
        self.llamadaEnCurso = llamada

    def obtenerDatosLlamada(self):
        nombreCliente = self.llamadaEnCurso.getNombreClienteLlamada()
        categoria = CategoriaLlamada.objects.filter(
            llamada=self.llamadaEnCurso
        )[0]
        opcion, subopcion = categoria.getDescripcionCompletaCategoriaYOpcion()
        return (nombreCliente, categoria, opcion, subopcion)

    def buscarValidaciones(self, subopcion):
        validacion = Validacion.objects.filter(subOpcion=subopcion)[0]
        return validacion

    def validarInformacionCliente(self, id_llamada, validacion, validacion_data):
        cliente = Cliente.objects.filter(llamada=id_llamada)[0]
        esInformacionCorrecta = cliente.esInformacionCorrecta(
            validacion=validacion,
            validacion_data=validacion_data
        )
        return esInformacionCorrecta
