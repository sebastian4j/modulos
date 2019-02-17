module modulos.tercero {
    requires modulos.segundo;
    exports com.sebastian.modulos.tercero;
    provides com.sebastian.modulos.segundo.exportado.Implementable
		with com.sebastian.modulos.tercero.Implementado;
}
