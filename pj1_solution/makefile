JCC = javac

JFLAGS = -g 

default: PixImage.class RunLengthEncoding.class Test.class

PixImage.class: PixImage.java
	$(JCC) $(JFLAGS) -cp "jai_core.jar:jai_codec.jar" PixImage.java

RunLengthEncoding.class: RunLengthEncoding.java
	$(JCC) $(JFLAGS) RunLengthEncoding.java

Test.class: Test.java
	$(JCC) $(JFLAGS) Test.java
	java Test

clean:
	$(RM) *.class
