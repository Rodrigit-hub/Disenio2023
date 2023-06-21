from datetime import datetime
from django.db import models
from .estado import Estado


class CambioEstado(models.Model):
    fechaHoraInicio = models.DateTimeField(default=datetime.now)
    estado = models.ForeignKey(Estado, on_delete=models.CASCADE)

    def __str__(self):
        return f'{self.estado.getNombre()} - {self.getFechaHoraInicio()}'

    def esEstadoInicial(self):
        return self.estado.getNombre()

    def esUltimoEstado(self):
        return self.estado.esFinalizada()

    def getFechaHoraInicio(self):
        return str(self.fechaHoraInicio)

    def getNombreEstado(self):
        return self.estado.getNombre()

    class Meta:
        db_table = 'cambio_estado'
