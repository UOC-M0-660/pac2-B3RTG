# UOC - PAC2

## Parte teórica

###  Ejercicio 8: Uso de librerías de terceros

1. ¿Qué librería has utilizado para cargar imágenes por URL? ¿Cómo es que funciona offline? 

En anteriores ocasiones, había utilizado la librería de terceros [Picasso](https://square.github.io/picasso/).
Debido a esto, pense que en esta ocasión podía utilizar otra librería, ya que es una de las referencias para este tipo de tareas,
pensé que seria util conocer como funciona. Finalmente escogí [glide](https://github.com/bumptech/glide)
Realmente el uso es muy similar en ambas y muy sencillo. Ademas, decir que funciona offline debido a que implementa cache de imagenes
y por lo tanto, una vez descargada, usará la cache mientras esté disponible



2. ¿Qué criterio conviene seguir para elegirlas?

Es importante tener en cuenta el soporte que tiene actualmente una lirería. Es decir, escoger una librería antigua, y poco utilizada
puede llevarnos a encontrarnos problemas y que estos no se solucionen.
Una libreria "viva" y muy utilizada, siempre estará mas pulida y tendras mas opciones de correción de ___BUGS___

3. ¿Qué problemas o riesgos asumimos cuando usamos?

Se asume que no tenemos el control sobre los problemas que pueda conllevar internamente la misma. Si se produce un problema interno
nosotros no podremos solucinarlo, y por lo general deberemos esperar a siguientes versiones de la misma para ver si se ha arreglado ese problema
o si han añadido esa nueva funcionalidad que necesitamos.

4. Agregar ejemplos de librerías de terceros ampliamente utilizadas en el desarrollo Android.
	- __Retrofit__: Esta librería puede ser la mas popular en android para realizar peticiones a una __API REST__
	- __GSON__: Libreria desarrollada por GOOGLE, utilizada para convertir objetos de __Java__ a __JSON__ y viceversa
	- __GLIDE__: Libreria utilizada en la PAC para carga de imagenes
	- __PICASSO__: Igual que glide, nos permite optimizar la carga de imagenes.
	- __LEAKCANARY__: Librería para la detección de fugas de memoria.
	