package com.github.cartagena.intercom.invite;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

public class CustomerRepository {

    private final Path filepath;
    private final Gson gson;

    public CustomerRepository(Path filepath) {
        if(isNull(filepath)) {
            throw new IllegalArgumentException("Filepath of customers file can't be null.");
        }

        this.filepath = filepath;
        this.gson = buildGson();
    }

    public List<Customer> readAllCustomers() {
        try {
            return Files.lines(filepath)
                    .filter(notEmpty())
                    .map(toCustomer())
                    .distinct()
                    .collect(toList());

        } catch (Exception e) {
            throw new InvalidCustomersFileException("File with customers does not exists or contains an invalid JSON line.", e);
        }
    }

    private Predicate<String> notEmpty() {
        return s -> !s.isEmpty();
    }

    private Function<String, Customer> toCustomer() {
        return s -> gson.fromJson(s, Customer.class);
    }

    private Gson buildGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

}
