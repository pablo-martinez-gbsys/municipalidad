
package com.gbsys.exotra.seguridad;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter3
    extends XmlAdapter<String, Date>
{


    public Date unmarshal(String value) {
        return (com.gbsys.exotra.xsd.DateTimeConverter.parseDate(value));
    }

    public String marshal(Date value) {
        return (com.gbsys.exotra.xsd.DateTimeConverter.printDate(value));
    }

}
