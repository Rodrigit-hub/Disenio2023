# controller/gestor_rta_operador.py
from ..models import Estado, Llamada, CategoriaLlamada
# Accion?

# Métodos relacionados con CambioEstado


class GestorRtaOperador:
    # llamadas = Llamada.objects.all()
    # estado = Llamada.objects.all()
    estadoEnCurso = None

    def nuevaRespuestaOperador(self):
        pass

    def buscarEstadoEnCurso(self):
        estados = Estado.objects.all()
        enCurso = list(filter(lambda estado: estado.esEnCurso(), estados))[0]
        self.estadoEnCurso = enCurso


# def obtener_cambio_estado(cambio_estado_id):
#     cambio_estado = CambioEstado.objects.get(pk=cambio_estado_id)
#     return cambio_estado

# # Métodos relacionados con Estado


# def obtener_estado(estado_id):
#     estado = Estado.objects.get(pk=estado_id)
#     return estado


# # Agrega aquí más métodos relacionados con otros modelos

# # class GestorRtaOperador:
# #     def __init__(self):
# #         pass

# #     def nuevaRespuestaOperador(self, ):
# #         pass

# #     def recibirLlamada(self, ):
# #         pass

# #     def buscarEstadoEnCurso(self, ):
# #         pass

# #     def obtenerDatosLlamada(self, ):
# #         pass

# #     def buscarValidaciones(self, ):
# #         pass

# #     def tomarDatosValidacion(self, ):
# #         pass

# #     def validarInformacionCliente(self, ):
# #         pass

# #     def tomarRtaOperador(self, ):
# #         pass

# #     def confirmar(self, ):
# #         pass

# #     def finalizarLlamada(self, ):
# #         pass

# #     def obtenerFechaYHoraActual(self, ):
# #         pass

# #     def buscarEstadoFinalizada(self, ):
# #         pass

# #     def finCU(self, ):
# #         pass
