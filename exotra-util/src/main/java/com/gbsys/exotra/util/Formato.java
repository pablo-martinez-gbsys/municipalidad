package com.gbsys.exotra.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enumerado para manejar los formatos de reportes del sistema.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes.
 * @version 1.0.0
 * @date 27/11/2017 20:30
 */
@RequiredArgsConstructor
public enum Formato {

    PDF("application/pdf"),
    XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    RTF("application/rtf"),
    HTML("text/html"),
    CSV("text/csv"),
    XML("application/xml");

    @Getter
    private final String mimeType;

}
