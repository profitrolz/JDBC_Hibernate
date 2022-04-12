package jm.task.core.jdbc.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Extractor<T> {
    @FunctionalInterface
    private interface FieldSetter<T> {
        void setColumn(ResultSet rs, T value) throws SQLException;
    }


    private final List<FieldSetter<T>> setters = new ArrayList<>();

    public static <T> Extractor<T>.Builder newExtractorBuilder() {
        return new Extractor<T>().new Builder();
    }


    public T extract(ResultSet rs, T obj) throws SQLException {
        for (FieldSetter<T> setter : setters) {
            setter.setColumn(rs, obj);
        }
        return obj;
    }

    public class Builder {
        private Builder() {

        }

        public Builder setInt(BiConsumer<T, Integer> setter, String field) {
            setters.add(((rs, value) -> setter.accept(value, rs.getInt(field))));
            return this;
        }

        public Builder setLong(BiConsumer<T, Long> setter, String field) {
            setters.add(((rs, value) -> setter.accept(value, rs.getLong(field))));
            return this;
        }

        public Builder setByte(BiConsumer<T, Byte> setter, String field) {
            setters.add(((rs, value) -> setter.accept(value, rs.getByte(field))));
            return this;
        }


        public Builder setString(BiConsumer<T, String> setter, String field) {
            setters.add(((rs, value) -> setter.accept(value, rs.getString(field))));
            return this;
        }

        public Extractor<T> build() {
            return Extractor.this;
        }

    }

}


