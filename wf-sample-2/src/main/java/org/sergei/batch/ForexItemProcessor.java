package org.sergei.batch;

import javax.batch.api.BatchProperty;
import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Named
@Dependent
public class ForexItemProcessor implements ItemProcessor {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("uuuuMMdd HHmmss");

    @Inject
    @BatchProperty
    private String symbol;

    @Override
    public Object processItem(final Object item) throws Exception {
        final List items = (List) item;
        return Arrays.asList(symbol,
                Timestamp.valueOf(LocalDateTime.parse((String) items.get(0), FORMATTER)),
                new BigDecimal((String) items.get(1)),
                new BigDecimal((String) items.get(2)),
                new BigDecimal((String) items.get(3)),
                new BigDecimal((String) items.get(4)),
                Integer.valueOf((String) items.get(5)));
    }
}
