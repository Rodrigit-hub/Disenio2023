from django import forms


class ValidacionForm(forms.Form):
    validacion = forms.TimeField(label="Ingrese Validacion", required=True)
