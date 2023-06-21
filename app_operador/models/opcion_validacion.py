from django.db import models

class OpcionValidacion(models.Model):
    correcta = models.BooleanField(default=False)
    descripcion = models.CharField(max_length=200)

    def __str__(self) -> str:
        return self.getDescripcion()

    def esCorrecta(self):
        return bool(self.correcta)

    def getDescripcion(self):
        return str(self.descripcion)
