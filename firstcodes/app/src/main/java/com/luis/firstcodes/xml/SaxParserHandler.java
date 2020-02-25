package com.luis.firstcodes.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

;import java.util.ArrayList;
import java.util.List;

public class SaxParserHandler extends DefaultHandler {
    private List<Person> persons;
    private Person person;
    private String tmpString;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        tmpString = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        switch (qName) {
            case "persons": {
                break;
            }
            case "name": {
                person.setName(tmpString);
                break;
            }
            case "age": {
                person.setAge(tmpString);
                break;
            }
            case "person": {
                persons.add(person);
                break;
            }
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        switch (qName) {
            case "person": {
                person = new Person();
                break;
            }
            case "persons": {
                person.setId(attributes.getValue("id"));
                break;
            }
            case "name": {
                break;
            }
            case "age": {
                break;
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        persons = new ArrayList<>();
    }

    public List<Person> getPersons() {
        return persons;
    }
}
