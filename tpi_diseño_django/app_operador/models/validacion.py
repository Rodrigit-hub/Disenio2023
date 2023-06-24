from django.db import models
from .sub_opcion_llamada import SubOpcionLlamada


class Validacion(models.Model):
    # CAMPO DEL TIPO ARCHIVO
    # audioMensajeValidacion = models.FileField(upload_to='audio_mensajes_validacion/')
    audioMensajeValidacion = models.CharField(max_length=255, null=True)
    nombre = models.CharField(max_length=100, null=True)
    subOpcion = models.ForeignKey(
        SubOpcionLlamada,
        on_delete=models.CASCADE
    )
    # Foreign Key
    # tipo = models.ForeignKey(TipoInformacion, on_delete=models.CASCADE)

    # opcionesValidacion = models.ManyToManyField(OpcionValidacion)

    def __str__(self):
        return self.getNombre()

    def getNombre(self):
        return str(self.nombre)

    def setNombre(self, nuevoNombre):
        self.nombre = nuevoNombre
        self.save()

    def getAudioMensajeValidacion(self):
        return str(self.audioMensajeValidacion)

    def setAudioMensajeValidacion(self, nuevoAudioMensajeValidacion):
        self.audioMensajeValidacion = nuevoAudioMensajeValidacion
        self.save()

    def getMensajeValidacion(self):
        return self.getNombre()

    class Meta:
        db_table = 'validacion'
