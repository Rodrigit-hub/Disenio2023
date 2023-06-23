# controller/gestor_rta_operador.py
from datetime import datetime
from ..models import Estado, Llamada, CategoriaLlamada
# Accion?

# Métodos relacionados con CambioEstado


class GestorRtaOperador:
    llamadaEnCurso = None
    operador = {
        'nombre': 'Test',
        'apellido': 'Test'
    }

    def __init__(self, llamada) -> None:
        self.setLlamadaEnCurso(llamada)

    def nuevaRespuestaOperador(self):
        self.recibirLlamada()

    def recibirLlamada(self):
        estadoEnCurso = self.buscarEstadoEnCurso()
        self.llamadaEnCurso.tomadaPorOperador(self.operador, estadoEnCurso)

    def buscarEstadoEnCurso(self):
        estados = Estado.objects.all()
        estadoEnCurso = next(
            (estado for estado in estados if estado.esEnCurso()), None)
        return estadoEnCurso

    # def buscarEstadoIniciada(self):
    #     estados = Estado.objects.all()
    #     estadoIniciada = list(
    #         filter(lambda estado: estado.esEnIniciada(), estados))[0]
    #     return estadoIniciada

    def obtenerFechaHoraActual(self):
        fechaHoraActual = datetime.now()
        return fechaHoraActual

    def getLlamadaEnCurso(self) -> Llamada:
        return self.llamadaEnCurso

    def setLlamadaEnCurso(self, llamada):
        self.llamadaEnCurso = llamada

    def buscarDatosLlamada(self):
        pass
