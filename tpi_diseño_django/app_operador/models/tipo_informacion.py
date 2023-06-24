from django.db import models
from .validacion import Validacion
from .cliente import Cliente


class TipoInformacion(models.Model):
    datoAValidar = models.CharField(max_length=255, default="")
    validacion = models.ForeignKey(Validacion, on_delete=models.CASCADE)
    cliente = models.ForeignKey(Cliente, on_delete=models.CASCADE)

    def __str__(self):
        return self.getDescripcion()

    def getDescripcion(self):
        return str(self.descripcion)

    def setDescripcion(self, nuevaDescripcion):
        self.descripcion = nuevaDescripcion
        self.save()

    class Meta:
        db_table = 'tipo_informacion'
