package pl.gov.coi.blox.repository.Blog;

//klasa odpowiadająca za rodzaje blogów

public enum CategoryType{
    KOD("Kod"),
    BŁĄD("Błąd"),
    PORADNIK("Poradnik");

    private String typeName;

    private CategoryType (String type){
        this.typeName = type;
    }

    public String getTypeName() {
        return typeName;
    }
}
