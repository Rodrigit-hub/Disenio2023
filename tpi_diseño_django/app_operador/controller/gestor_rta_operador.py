# controller/gestor_rta_operador.py
from datetime import datetime
from ..models import Estado, Llamada, CategoriaLlamada
# Accion?

# Métodos relacionados con CambioEstado


class GestorRtaOperador:
    categoriaLlamadaSeleccionada = None

    def nuevaRespuestaOperador(self):
        self.recibirLlamada()

    def recibirLlamada(self):
        self.llamadaActual = Llamada.objects.filter(
            estadoActual__nombre='iniciada')
        print(self.llamadaActual)

    def buscarEstadoEnCurso(self):
        estados = Estado.objects.all()
        enCurso = list(filter(lambda estado: estado.esEnCurso(), estados))[0]
        return enCurso

    def buscarEstadoIniciada(self):
        estados = Estado.objects.all()
        estadoIniciada = list(
            filter(lambda estado: estado.esEnIniciada(), estados))[0]
        return estadoIniciada

    def obtenerFechaHoraActual(self):
        fechaHoraActual = datetime.now()
        return fechaHoraActual

    def getLlamadaEnCurso(self) -> Llamada:
        return self.llamadaEnCurso

    def setLlamadaEnCurso(self, llamada):
        self.llamadaEnCurso = llamada

    def buscarDatosLlamada(self):
        pass
