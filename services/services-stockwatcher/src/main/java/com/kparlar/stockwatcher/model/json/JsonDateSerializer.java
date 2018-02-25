package com.kparlar.stockwatcher.model.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.kparlar.stockwatcher.util.StockWatcherConstantUtil;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class JsonDateSerializer extends JsonSerializer<Date> {



  @Override
  public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider provider)
      throws IOException {

    SimpleDateFormat dateFormat = new SimpleDateFormat(StockWatcherConstantUtil.JSON_DATE_FORMAT);

    String formattedDate = dateFormat.format(date);

    jsonGenerator.writeString(formattedDate);
  }
}
