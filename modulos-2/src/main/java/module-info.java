module modulos.segundo {
    requires modulos.comun;
    exports com.sebastian.modulos.segundo.exportado;
    uses com.sebastian.modulos.segundo.exportado.Implementable;
}
