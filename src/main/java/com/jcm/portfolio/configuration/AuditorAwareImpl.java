package com.jcm.portfolio.configuration;

import java.util.Optional;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

  @Override
  public @NotNull Optional<String> getCurrentAuditor() {
    //Anadir el usuario del contexto de seguridad cuando se implemente correctamente spring security.
    return Optional.of(UUID.randomUUID().toString());
  }
}
