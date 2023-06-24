from django.utils import timezone
from django.db import models
from .estado import Estado
from .llamada import Llamada


class CambioEstado(models.Model):
    fechaHoraCambio = models.DateTimeField(default=timezone.now)
    # fechaHoraFin = models.DateTimeField(null=True)
    estado = models.ForeignKey(
        Estado,
        on_delete=models.CASCADE
    )
    llamada = models.ForeignKey(Llamada, on_delete=models.CASCADE)

    # def new(self, estado, llamada):
    #     self.setEstado(estado)
    #     self.setLlamada(llamada)

    # Constructor para no romper el funcionamiento del model de Django

    # def __init__(self, llamada=None, estado=None, *args, **kwargs):
    #     super().__init__(*args, **kwargs)
    #     pass

    def __str__(self):
        return f'{self.estado.getNombre()} - {self.getFechaHoraCambio()}'

    def setEstado(self, nuevoEstado):
        self.estado = nuevoEstado
        self.save()

    def esEstadoInicial(self):
        return self.estado.getNombre()

    def esUltimoEstado(self):
        return self.estado.esFinalizada()

    def getFechaHoraCambio(self):
        return str(self.fechaHoraCambio)

    def getNombreEstado(self):
        return self.estado.getNombre()

    def getLlamada(self):
        return self.llamada

    def setLlamada(self, llamada):
        self.llamada = llamada
        self.save()

    class Meta:
        db_table = 'cambio_estado'
