from django.db import models
from .cliente import Cliente
from .estado import Estado
from .cambio_estado import CambioEstado
from .sub_opcion_llamada import SubOpcionLlamada


class Llamada(models.Model):
    # def __init__(self, parametro1):
    #     print(parametro1)

    descripcionOperador = models.CharField(max_length=255, null=True)
    detalleAccionRequerida = models.TextField(null=True)
    duracion = models.IntegerField(default=0)
    encuestaEnviada = models.BooleanField(null=True)
    observacionAuditor = models.TextField(null=True)

    # Foreign Keys
    cliente = models.ForeignKey(Cliente, on_delete=models.CASCADE)
    subOpcion = models.ForeignKey(
        SubOpcionLlamada, on_delete=models.CASCADE, null=True)
    estadoActual = models.ForeignKey(Estado, on_delete=models.CASCADE)

    # VER ESTO
    cambiosEstado = models.ManyToManyField(CambioEstado)

    def __str__(self) -> str:
        return f'{self.cliente.getNombre()} - {self.estadoActual.getNombre()}'

    def calcularDuracion(self):
        pass

    def tomadaPorOperador(self):
        cambio_estado = CambioEstado()
        cambio_estado.save()
        self.cambiosEstado.add(cambio_estado)
        print(self.cambiosEstado)

    def esDePeriodo(self, fecha_inicio, fecha_fin):
        # Lógica para verificar si la llamada pertenece a un periodo específico
        pass

    def getDuracion(self):
        return int(self.duracion)

    def setDuracion(self, duracion):
        self.duracion = duracion
        self.save()

    def getNombreCliente(self):
        return self.cliente.getNombre()

    def setDescripcionOperador(self, descripcion):
        self.descripcionOperador = descripcion
        self.save()

    def setEstadoActual(self, nuevo_estado):
        self.estadoActual = nuevo_estado
        self.save()

    class Meta:
        db_table = 'llamada'
