from django.db import models
from .validacion import Validacion
from .tipo_informacion import TipoInformacion
from .opcion_validacion import OpcionValidacion


class InformacionCliente(models.Model):
    datoAValidar = models.CharField(max_length=255, null=True)

    # # FOREIGN KEYS
    # tipoInformacion = models.ForeignKey(TipoInformacion, on_delete=models.CASCADE)
    # validacion = models.ForeignKey(Validacion, on_delete=models.CASCADE)

    # # RELACION MUCHOS A MUCHOS
    # opcionesValidacion = models.ManyToManyField(OpcionValidacion)

    def __str__(self):
        return f'{self.getDatoAValidar()}'

    def esInformacionCorrecta(self, informacion):
        pass
        # return informacion == self.datoAValidar

    def esValidacion(self, validacion_a_comparar: Validacion):
        # Compara la instancia actual de validacion con el parámetro proporcionado
        return self.validacion.getNombre() == validacion_a_comparar.getNombre()

    def getDatoAValidar(self):
        return str(self.datoAValidar)

    def setDatoAValidar(self, nuevoDato):
        self.datoAValidar = nuevoDato
        self.save()

    class Meta:
        db_table = 'informacion_cliente'
