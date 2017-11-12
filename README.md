# Klingon Translator
Test code from jexia.com

A Springboot project that translates english words or sentences in Klingon but shows it in hexadecimal format.
If the name passed is a STAR TREK character, the application also search for its species in [STAPI](http://stapi.co/).

The DictionaryService class receives an alphabet to translate something.
That way in future we could pass diferents alphabets and DictionaryService will use it to translate correctly.
All futures alphabets should implement Alphabet.java interface. In order to implement the knowing by DictionaryService method
called 'translate'.

To call stapi.co API I've chose RestTemplate from Spring.
First, I've to call "http://stapi.co/api/v1/rest/character/search" via POST method passing the name of character to find its UID.
After that, I've used this character UID to find its species calling via GET method the "http://stapi.co/api/v1/rest/character".
The json returned here have an array called "characterSpecies".

The application can be executed via 'translate_to_klingon' bash file.
Please, download the Klingon Translator project and in the root directory run the two commands below:
First, make sure that the file is executable:
```
chmod u+x filename
```
Then, run the project:
```
./translate_to_klingon Uhura
```

