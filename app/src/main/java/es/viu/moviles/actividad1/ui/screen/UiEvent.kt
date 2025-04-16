package es.viu.moviles.actividad1.ui.screen

sealed interface UiEvent {
    data class ShowMessage(val message: String) : UiEvent

}