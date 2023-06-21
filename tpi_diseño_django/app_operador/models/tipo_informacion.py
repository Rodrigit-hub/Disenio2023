from django.db import models


class TipoInformacion(models.Model):
    descripcion = models.CharField(max_length=255, default="")

    def __str__(self):
        return self.getDescripcion()

    def getDescripcion(self):
        return str(self.descripcion)

    def setDescripcion(self, nuevaDescripcion):
        self.descripcion = nuevaDescripcion
        self.save()

    class Meta:
        db_table = 'tipo_informacion'
