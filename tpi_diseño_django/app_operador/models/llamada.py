from django.db import models
from .cliente import Cliente
from .estado import Estado


class Llamada(models.Model):
    descripcionOperador = models.CharField(max_length=255, null=True)
    detalleAccionRequerida = models.TextField(null=True)
    duracion = models.IntegerField(default=0)
    encuestaEnviada = models.BooleanField(null=True)
    observacionAuditor = models.TextField(null=True)
    operador = None

    # Foreign Keys
    cliente = models.ForeignKey(Cliente, on_delete=models.CASCADE)
    estadoActual = models.ForeignKey(Estado, on_delete=models.CASCADE)

    def __str__(self) -> str:
        return f'{self.cliente.getNombre()} - {self.estadoActual.getNombre()}'

    def calcularDuracion(self):
        pass

    def tomadaPorOperador(self, operador, fechaHoraActual, estadoEnCurso):
        self.estadoActual = estadoEnCurso
        self.operador = operador
        from .cambio_estado import CambioEstado
        CambioEstado.objects.create(
            llamada=self,
            estado=estadoEnCurso,
            fechaHoraCambio=fechaHoraActual
        )
        self.save()

    def finalizarLlamada(self, fechaHoraActual, estadoFinalizada):
        self.estadoActual = estadoFinalizada
        from .cambio_estado import CambioEstado
        CambioEstado.objects.create(
            llamada=self,
            estado=estadoFinalizada,
            fechaHoraCambio=fechaHoraActual
        )
        self.estadoActual = estadoFinalizada
        self.save()

    def getNombreClienteLlamada(self):
        return self.cliente.getNombre()

    def getEstadoActual(self):
        return self.estadoActual.getNombre()

    def setEstadoActual(self, estado: Estado):
        self.estadoActual = estado
        self.save()

    def getDuracion(self):
        return int(self.duracion)

    def setDuracion(self, duracion):
        self.duracion = duracion
        self.save()

    def setDescripcionOperador(self, descripcion):
        self.descripcionOperador = descripcion
        self.save()

    class Meta:
        db_table = 'llamada'
