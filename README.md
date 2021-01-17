# Weather

> Aceasta aplicatie permite vizualizarea detaliilor vremii dintr-un oras pe baza datelor puse la dispozitie de [openweathermap.org][df1].

Pacehtul de baza al aplicatiei este *ro.mta.se.lab*. Acesta cuprinde la randul lui mai multe pachete:
 - *api*: se ocupa de aducerea si parsarea datelor puse la dispozitie de openweathermap
 - *parser*: se ocupa de parsarea fisierului ce contine datele initiale
 - *logger*: se ocupa de salvarea interogarilor anterioare intr-un fisier
 - *model*: gestioneaza partea de model a aplicatiei si contine clasele in care se face maparea datelor
 - *controller*: se ocupa de modul in care se comporta interfata aplicatiei
 
 In cadrul aplicatiei avem doua combobox-uri, unul pentru lista de tari si unul pentru lista de orase. In momentul in care se selecteza
 o tara va fi populat combobox-ul de orase iar in momentul in care se alege orasul detaliile vremii vor fi aduse de pe 
 [openweathermap.org][df1] si vor fi afisate in interfata. Totodata aceste date vor fi salvate si in fisierul care contine intoricul
 interogarilor si care se gaseste in folder-ul parinte al aplicatiei sub numele de *logfile*.
 Interfata grafica mai pune la dispozitie si un buton de *refresh* al datelor pentru orasul pentru care vremea a fost 
 interogata ultima data.
 
 Pentru testarea functionalitatilor aplicatiei au fost create teste unitare si de integrare.


















[df1]: <https://rapidapi.com/blog/lp/openweathermap/?utm_source=google&utm_medium=cpc&utm_campaign=Alpha&utm_term=openweathermap_e&gclid=Cj0KCQiA3Y-ABhCnARIsAKYDH7uu_5VZlnAPqNhaYWJnkJNQQrxgxBOd4JtZIKYMhZVj7aVRl2zLcqQaApCIEALw_wcB>
