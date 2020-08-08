# Search-words-in-websites

Wyszukiwarka pojedyńczych słów z podanej strony internetowej. Po uruchomieniu programu należy wpisać adres strony
internetowej, z której zostaną podbrane pojedyńczo występujące wyrazy i zapisane w pliku o nazwie "popular_words.txt".
Następnie program poprosi nas o wpisanie pojedyńczysz haseł, które chcielibyśmy znaleźć na wybranej stronie internetowej.
Jeżeli podane hasło występuje na stronie internetowej, po zakończeniu działania programu zostanie zapisane do pliku 
"filtered_popular_words.txt".

## Jak uruchomić 
Należy uruchomić w terminalu, będąc w katalogu głównym, polecenia:

```
javac -cp lib/jsoup-1.13.1.jar pl/javalearn/Search.java
java -cp .:lib/jsoup-1.13.1.jar pl.javalearn.Search
```