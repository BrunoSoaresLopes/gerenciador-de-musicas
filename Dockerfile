FROM openjdk:21

WORKDIR /app

COPY ./src /app/src
COPY ./lib/sqlite-jdbc-3.50.3.0.jar /app/lib/sqlite-jdbc-3.50.3.0.jar
COPY ./musicas.db /app/musicas.db

RUN javac -cp "lib/sqlite-jdbc-3.50.3.0.jar" \
    src/view/MenuPrincipal.java \
    src/dao/MusicaDAO.java \
    src/database/Conexao.java \
    src/database/Inicializador.java \
    src/model/Musica.java

CMD ["java", "-cp", "lib/sqlite-jdbc-3.50.3.0.jar:src", "view.MenuPrincipal"]
