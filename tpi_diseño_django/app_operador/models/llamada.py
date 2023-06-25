from django.db import models
from .cliente import Cliente
from .estado import Estado


class Llamada(models.Model):
    descripcionOperador = models.CharField(max_length=255, null=True)
    detalleAccionRequerida = models.TextField(null=True)
    duracion = models.CharField(max_length=50)
    encuestaEnviada = models.BooleanField(null=True)

    # Foreign Keys
    cliente = models.ForeignKey(Cliente, on_delete=models.CASCADE)
    estadoActual = models.ForeignKey(Estado, on_delete=models.CASCADE)

    def __str__(self) -> str:
        return f'{self.cliente.getNombre()} - {self.estadoActual.getNombre()}'

    def calcularDuracion(self):
        pass

    def tomadaPorOperador(self, fechaHoraActual, estadoEnCurso):
        self.estadoActual = estadoEnCurso
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
        self.calcularDuracion()

    def calcularDuracion(self):
        from .cambio_estado import CambioEstado
        primer_cambio = CambioEstado.objects.filter(
            llamada=self
        ).first()
        ultimo_cambio = CambioEstado.objects.filter(
            llamada=self
        ).last()

        diferencia = str(ultimo_cambio.fechaHoraCambio -
                         primer_cambio.fechaHoraCambio)
        self.setDuracion(diferencia)
        return diferencia

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
