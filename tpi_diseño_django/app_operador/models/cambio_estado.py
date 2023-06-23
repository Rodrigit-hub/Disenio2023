from django.utils import timezone
from django.db import models
from .estado import Estado


class CambioEstado(models.Model):
    fechaHoraInicio = models.DateTimeField(default=timezone.now)
    fechaHoraFin = models.DateTimeField(null=True)
    estado = models.ForeignKey(
        Estado,
        on_delete=models.CASCADE
    )
    llamada = None

    # def new(self, estado, llamada):
    #     self.setEstado(estado)
    #     self.setLlamada(llamada)

    # Constructor para no romper el funcionamiento del model de Django

    # def __init__(self, llamada=None, estado=None, *args, **kwargs):
    #     super().__init__(*args, **kwargs)
    #     if llamada is not None:
    #         self.llamada = llamada
    #     if estado is not None:
    #         self.estado = estado

    def __str__(self):
        return f'{self.estado.getNombre()} - {self.getFechaHoraInicio()}'

    def setEstado(self, nuevoEstado):
        self.estado = nuevoEstado
        self.save()

    def esEstadoInicial(self):
        return self.estado.getNombre()

    def esUltimoEstado(self):
        return self.estado.esFinalizada()

    def getFechaHoraInicio(self):
        return str(self.fechaHoraInicio)

    def getNombreEstado(self):
        return self.estado.getNombre()

    def getLlamada(self):
        return self.llamada

    def setLlamada(self, llamada):
        self.llamada = llamada
        self.save()

    class Meta:
        db_table = 'cambio_estado'
