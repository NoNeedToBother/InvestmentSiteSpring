package ru.kpfu.itis.paramonov.mappers;

public interface ModelMapper<M, D> {
    D fromModel(M model);

    default M fromDto(D dto) {
        throw new RuntimeException("Not implemented");
    }
}
