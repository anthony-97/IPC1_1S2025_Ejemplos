Algoritmo verificacionZonaMinima
	Escribir "Ingrese nota primer parcial (sobre 15)"
	Leer valor1
	Escribir "Ingrese nota segundo parcial (sobre 15)"
	Leer valor2
	Escribir "Ingrese nota tercer parcial (sobre 15)"
	Leer valor3
	Escribir "Ingrese nota actividades (sobre 20)"
	Leer valor4
	zona = valor1+valor2+valor3+valor4
	Si zona <= 36 Entonces
		Escribir "El estudiante no tiene derecho a examen final y perdio el curso. :("
	Sino 
		notaExamen = 61-zona
		Escribir "El estudiante puede hacer su examen final sin problema, necesita ", notaExamen, puntos
	FinSi
	
FinAlgoritmo
