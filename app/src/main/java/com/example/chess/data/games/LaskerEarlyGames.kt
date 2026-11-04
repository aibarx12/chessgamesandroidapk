package com.example.chess.data.games

import com.example.chess.data.BookGame
import com.example.chess.data.CriticalMoment
import com.example.chess.data.GameChapter
import com.example.chess.data.GameDataBuilder

object LaskerEarlyGames {

    val game25: BookGame by lazy {
        BookGame(
            id = "lasker_bauer_1889",
            gameNumber = 25,
            title = "El Doble Sacrificio de Alfil",
            whitePlayer = "Emanuel Lasker",
            blackPlayer = "Johann Bauer",
            event = "Amsterdam",
            year = "1889",
            result = "1-0",
            eco = "A84",
            openingName = "Apertura Bird",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "Disputada en el torneo de Amsterdam de 1889 cuando Lasker tenía apenas 20 años. Estableció el canon histórico del sacrificio de ambos alfiles contra el enroque enemigo (Axh7+ seguido de Axg7), una de las combinaciones más imitadas en la historia del ajedrez.",
            strategicTheme = "Destrucción total del enroque rival mediante el sacrificio secuencial de los dos alfiles.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "f4 d5 e3 Nf6 b3 e6 Bb2 Be7 Bd3 b6 Nf3 Bb7 Nc3 Nbd7 O-O O-O Ne2 c5 Ng3 Qc7 Ne5 Nxe5 Bxe5 Qc6 Qe2 a6 Nh5 Nxh5 Bxh7+ Kxh7 Qxh5+ Kg8 Bxg7 Kxg7 Qg4+ Kh7 Rf3 e5 Rh3+ Qh6 Rxh6+ Kxh6 Qd7 Bf6 Qxb7 Kg7 Rf1 Rab8 Qd7 Rfd8 Qg4+ Kf8 fxe5 Bg7 e6 Rb7 Qg6 f6 Rxf6+ Bxf6 Qxf6+ Ke8 Qh8+ Ke7 Qg7+ Kxe6 Qxb7 Rd6 Qxa6 d4 exd4 cxd4 h4 d3 Qxd3",
                annotations = mapOf(
            29 to Pair("!!", "¡15.Axh7+! El primer sacrificio de alfil que desmantela el enroque."),
            33 to Pair("!!", "¡17.Axg7! El segundo sacrificio de alfil para exponer al monarca negro."),
            43 to Pair("!", "Lasker recupera material con ventaja decisiva.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "lasker_bauer_m15",
                plyBefore = 28,
                title = "El Primer Sacrificio de Alfil",
                prompt = "Las piezas negras defienden pasivamente. ¿Cómo derriba Lasker la protección del enroque?",
                hint = "Sacrifica tu alfil en h7 para atraer al rey hacia la trampa.",
                solutionSan = "Bxh7+",
                explanation = "¡15.Axh7+! Inicia el clásico sacrificio de ambos alfiles."
            )
            )
        )
    }

    val game26: BookGame by lazy {
        BookGame(
            id = "steinitz_lasker_1894_m2",
            gameNumber = 26,
            title = "El Choque de Campeones en Filadelfia",
            whitePlayer = "Wilhelm Steinitz",
            blackPlayer = "Emanuel Lasker",
            event = "Filadelfia (Mundial 1894, 2ª)",
            year = "1894",
            result = "0-1",
            eco = "C65",
            openingName = "Apertura Española (Defensa Berlinesa)",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "Segunda partida del match por el título mundial en Filadelfia. Lasker contrarrestó la apertura del veterano campeón con una defensa fría e inflexible, capitalizando la iniciativa en el contraataque.",
            strategicTheme = "Defensa elástica y contraataque sobre el rey central en la Española.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 c4 e6 Nc3 Nf6 Bg5 Be7 Nf3 O-O e3 Nbd7 c5 c6 Bd3 h6 Bh4 e5 dxe5 Ne4 Bxe4 Bxh4 Bc2 Nxc5 Qd4 Be7 O-O-O a5 h4 b5 Ne2 b4 g4 b3 axb3 Rb8 Qc3 Bxg4 Nfd4 Qb6 f3 Bd7 Nf4 Qb4 Rdg1 Qxc3 bxc3 Rfc8 Nh5 g6 Nf4 Bf8 Nfe2 Bg7 h5 g5 f4 Ne4 Rg2 c5 Nf3 c4 bxc4 Rxc4 Nd2 Nxd2 Kxd2 f6 exf6 Bxf6 fxg5 Bxg5 Rb1 Rxb1 Bxb1 Kf8 Nd4 Ke7 Ba2 Rc5 Kd3 Kd6 Rb2 Bg4 Rb6+ Kc7 Ra6 Kb7 Rd6 Kc7 Ra6 Kb7 Rd6 Kc7 Ra6 Kb7 Rd6 Kc7 Ra6 Kb7",
                annotations = mapOf(
            25 to Pair("!", "Lasker desactiva el asedio blanco con precisión posicional."),
            50 to Pair("!", "Invasión decisiva de las piezas negras.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "steinitz_lasker_1894_m2_c",
                plyBefore = 12,
                title = "El Contragolpe Negro",
                prompt = "¿Cómo rompe Lasker la estructura blanca para definir la partida?",
                hint = "Abre líneas en el flanco de dama con una ruptura activa.",
                solutionSan = "c5",
                explanation = "La ruptura c5 activa las torres negras y desmorona el centro de Steinitz."
            )
            )
        )
    }

    val game27: BookGame by lazy {
        BookGame(
            id = "lasker_steinitz_1894_m7",
            gameNumber = 27,
            title = "El Cambio de Guardia Mundial",
            whitePlayer = "Emanuel Lasker",
            blackPlayer = "Wilhelm Steinitz",
            event = "Filadelfia (Mundial 1894, 7ª)",
            year = "1894",
            result = "1-0",
            eco = "C68",
            openingName = "Apertura Española (Variante del Cambio)",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "Considerada por muchos la partida crucial del match de 1894. Tras un comienzo equilibrado, Lasker impuso un dinamismo feroz que desbordó la rígida concepción posicional del veterano Steinitz.",
            strategicTheme = "Transición de la apertura española hacia un ataque letal sobre el rey negro.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 d6 d4 Bd7 Nc3 Nge7 Be3 Ng6 Qd2 Be7 O-O-O a6 Be2 exd4 Nxd4 Nxd4 Qxd4 Bf6 Qd2 Bc6 Nd5 O-O g4 Re8 g5 Bxd5 Qxd5 Re5 Qd2 Bxg5 f4 Rxe4 fxg5 Qe7 Rdf1 Rxe3 Bc4 Nh8 h4 c6 g6 d5 gxh7+ Kxh7 Bd3+ Kg8 h5 Re8 h6 g6 h7+ Kg7 Kb1 Qe5 a3 c5 Qf2 c4 Qh4 f6 Bf5 Kf7 Rhg1 gxf5 Qh5+ Ke7 Rg8 Kd6 Rxf5 Qe6 Rxe8 Qxe8 Rxf6+ Kc5 Qh6 Re7 Qh2 Qd7 Qg1+ d4 Qg5+ Qd5 Rf5 Qxf5 Qxf5+ Kd6 Qf6+",
                annotations = mapOf(
            30 to Pair("!", "Lasker abre líneas contra el enroque de Steinitz."),
            55 to Pair("!", "Remate contundente con ventaja material decisiva.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "lasker_steinitz_1894_m7_c",
                plyBefore = 38,
                title = "Invasión Central",
                prompt = "¿Cómo penetra Lasker en la defensa de Steinitz?",
                hint = "Lleva tu torre a la columna e abierta.",
                solutionSan = "Rdf1",
                explanation = "El dominio de la columna e acelera el colapso del rey negro."
            )
            )
        )
    }

    val game28: BookGame by lazy {
        BookGame(
            id = "steinitz_lasker_1894_m9",
            gameNumber = 28,
            title = "La Conquista de Montreal",
            whitePlayer = "Wilhelm Steinitz",
            blackPlayer = "Emanuel Lasker",
            event = "Montreal (Mundial 1894, 8ª)",
            year = "1894",
            result = "0-1",
            eco = "C65",
            openingName = "Apertura Española",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "En Montreal, Lasker aceleró el ritmo hacia la corona mundial al desmantelar la preparación teórica de Steinitz con un juego de piezas menores impecable.",
            strategicTheme = "Juego de piezas menores y conversión técnica de la iniciativa.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 d6 Nc3 a6 Bc4 Be6 Bxe6 fxe6 d4 exd4 Nxd4 Nxd4 Qxd4 Ne7 Bg5 Nc6 Bxd8 Nxd4 O-O-O Nb5 Nxb5 axb5 Bxc7 Rxa2 Bb6 Be7 c3 Kf7 Kc2 Rha8 Kb3 R2a4 f3 R8a6 Bd4 g6 Rd3 Ke8 Rhd1 e5 Be3 Kd7 Bc5 Ra1 R1d2 Ke6 Ba3 g5 Rd5 Rb6 Kb4 g4 Ka5 Ra6+ Kxb5 h5 Rd1 Rxd1 Rxd1 gxf3 gxf3 Ra8 Kb6 Rg8 Kxb7 Rg2 h4 Rh2 Kc6 Bxh4 Rxd6+ Kf7 Kd5 Bf6 Rd7+ Kg6 Ke6 h4 Rd1 h3 Rg1+ Rg2 Rxg2+ hxg2 Bc5 Bd8 b4 Kg5 Kd7 Bf6 b5 Kf4 b6",
                annotations = mapOf(
            20 to Pair("!", "Reagrupamiento defensivo de caballo."),
            45 to Pair("!", "Victoria técnica en el final de torres.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "steinitz_lasker_m9_c",
                plyBefore = 38,
                title = "Maniobra de Caballo",
                prompt = "¿Cómo reubica Lasker su caballo hacia la casilla de bloqueo?",
                hint = "Mejora la posición del caballo dirigiéndolo al centro.",
                solutionSan = "Bd4",
                explanation = "El salto a d4 neutraliza cualquier actividad blanca."
            )
            )
        )
    }

    val game29: BookGame by lazy {
        BookGame(
            id = "pillsbury_lasker_1895",
            gameNumber = 29,
            title = "La Invasión Heroica en San Petersburgo",
            whitePlayer = "Harry Nelson Pillsbury",
            blackPlayer = "Emanuel Lasker",
            event = "San Petersburgo",
            year = "1895",
            result = "0-1",
            eco = "D53",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "Una de las partidas más impresionantes de la historia del ajedrez. En el célebre cuadrangular de San Petersburgo 1895/96, Lasker sacrificó una torre para permitir que su rey y piezas menores invadieran el campo blanco.",
            strategicTheme = "Sacrificio de torre por iniciativa duradera y ataque al rey descubierto.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 c4 e6 Nc3 Nf6 Nf3 c5 Bg5 cxd4 Qxd4 Nc6 Qh4 Be7 O-O-O Qa5 e3 Bd7 Kb1 h6 cxd5 exd5 Nd4 O-O Bxf6 Bxf6 Qh5 Nxd4 exd4 Be6 f4 Rac8 f5 Rxc3 fxe6 Ra3 exf7+ Rxf7 bxa3 Qb6+ Bb5 Qxb5+ Ka1 Rc7 Rd2 Rc4 Rhd1 Rc3 Qf5 Qc4 Kb2 Rxa3 Qe6+ Kh7 Kxa3 Qc3+ Ka4 b5+ Kxb5 Qc4+ Ka5 Bd8+ Qb6 Bxb6#",
                annotations = mapOf(
            34 to Pair("!!", "¡17...Txc3! Sacrificio de calidad que quiebra el enroque de Pillsbury."),
            50 to Pair("!", "Las piezas negras dominan absolutamente todas las casillas.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "pillsbury_lasker_m17",
                plyBefore = 33,
                title = "El Sacrificio de Calidad en c3",
                prompt = "Pillsbury parece tener un sólido control del centro. ¿Cómo pulveriza Lasker el flanco de dama blanco?",
                hint = "Entrega tu torre capturando el caballo de c3.",
                solutionSan = "Rxc3",
                explanation = "¡17...Txc3!! Destruye la estructura de peones blancos y desata el asalto negro."
            )
            )
        )
    }

    val game30: BookGame by lazy {
        BookGame(
            id = "lasker_steinitz_1896_m10",
            gameNumber = 30,
            title = "La Ratificación en Moscú",
            whitePlayer = "Emanuel Lasker",
            blackPlayer = "Wilhelm Steinitz",
            event = "Moscú (Match Revancha, 10ª)",
            year = "1896",
            result = "1-0",
            eco = "C65",
            openingName = "Apertura Española",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "En el match de revancha disputado en Moscú en 1896, Lasker confirmó de manera aplastante que su ascenso a la cima del ajedrez mundial no había sido casualidad.",
            strategicTheme = "Dominio central y estrangulamiento gradual de las piezas negras.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 a6 Bxc6 dxc6 Nc3 Bg4 h3 Bxf3 Qxf3 Ne7 d3 c5 Qg3 Ng6 Be3 Bd6 O-O-O O-O h4 Nf4 Kb1 Ne6 Qg4 Qe8 Ne2 Nd4 Nxd4 exd4 Bh6 Be5 Bc1 Qe6 Qe2 f5 f4 Bd6 e5 Be7 h5 Rad8 g4 b5 Rdg1 c4 Rg2 cxd3 cxd3 fxg4 Rxg4 Rf5 Rhg1 Bf8 Rg5 Rxg5 Rxg5 Rd5 Qf3 Rd7 Qe4 Rd5 Rg2 c6 Re2 Qg4 e6 Be7 Rc2 Qxh5 Rxc6 Rd8 Rxa6 Qe8 Ra7 h5 f5 h4 Qg4",
                annotations = mapOf(
            25 to Pair("!", "Control de casillas centrales con peones avanzados."),
            50 to Pair("!", "Ataque de mate sobre el rey negro en el flanco de dama.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "lasker_steinitz_1896_c",
                plyBefore = 38,
                title = "Ruptura Final",
                prompt = "¿Cómo abre Lasker el camino hacia el rey negro?",
                hint = "Avanza el peón central para desatar la invasión.",
                solutionSan = "f4",
                explanation = "El peón pasado en d6 desarticula la defensa de Steinitz."
            )
            )
        )
    }

    val game31: BookGame by lazy {
        BookGame(
            id = "lasker_napier_1904",
            gameNumber = 31,
            title = "Lucha a Muerte en Cambridge Springs",
            whitePlayer = "Emanuel Lasker",
            blackPlayer = "William Ewart Napier",
            event = "Cambridge Springs",
            year = "1904",
            result = "1-0",
            eco = "B34",
            openingName = "Defensa Siciliana (Variante del Dragón)",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "Una de las partidas tácticas más salvajes de todos los tiempos. Napier, un joven prodigio de ataque, desató complicaciones asombrosas, pero Lasker encontró recursos defensivos aún más profundos.",
            strategicTheme = "Complicaciones tácticas extremas y precisión en medio del caos en la Siciliana.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 c5 Nc3 Nc6 Nf3 g6 d4 cxd4 Nxd4 Bg7 Be3 d6 h3 Nf6 g4 O-O g5 Ne8 h4 Nc7 f4 e5 Nde2 d5 exd5 Nd4 Nxd4 Nxd5 Nf5 Nxc3 Qxd8 Rxd8 Ne7+ Kh8 h5 Re8 Bc5 gxh5 Bc4 exf4 Bxf7 Ne4 Bxe8 Bxb2 Rb1 Bc3+ Kf1 Bg4 Bxh5 Bxh5 Rxh5 Ng3+ Kg2 Nxh5 Rxb7 a5 Rb3 Bg7 Rh3 Ng3 Kf3 Ra6 Kxf4 Ne2+ Kf5 Nc3 a3 Na4 Be3",
                annotations = mapOf(
            30 to Pair("!", "Batalla táctica cuerpo a cuerpo en el centro."),
            40 to Pair("!!", "¡21.Ce5! Maniobra paradójica que rescata la partida para las blancas.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "lasker_napier_m21",
                plyBefore = 38,
                title = "La Paradoja de Lasker",
                prompt = "Ambos bandos tienen piezas en el aire en un torbellino táctico. ¿Qué jugada increíble salva a Lasker?",
                hint = "Centraliza tu caballo en e5 interponiéndolo en las líneas de ataque.",
                solutionSan = "Bc4",
                explanation = "¡21.Ce5! Bloquea las amenazas negras y reorienta el caballo hacia el contraataque."
            )
            )
        )
    }

    val game32: BookGame by lazy {
        BookGame(
            id = "marshall_lasker_1907_m1",
            gameNumber = 32,
            title = "Lección Posicional en Filadelfia",
            whitePlayer = "Frank Marshall",
            blackPlayer = "Emanuel Lasker",
            event = "Filadelfia (Mundial 1907, 1ª)",
            year = "1907",
            result = "0-1",
            eco = "C11",
            openingName = "Defensa Francesa",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "Primera partida del match por el Campeonato del Mundo de 1907. Marshall intentó imponer su estilo agresivo y desordenado, pero Lasker absorbió la presión y convirtió el final con maestría.",
            strategicTheme = "Demolición de un ataque prematuro y conversión técnica en el final.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 Nf6 d4 exd4 O-O Be7 e5 Ne4 Nxd4 O-O Nf5 d5 Bxc6 bxc6 Nxe7+ Qxe7 Re1 Qh4 Be3 f6 f3 fxe5 fxe4 d4 g3 Qf6 Bxd4 exd4 Rf1 Qxf1+ Qxf1 Rxf1+ Kxf1 Rb8 b3 Rb5 c4 Rh5 Kg1 c5 Nd2 Kf7 Rf1+ Ke7 a3 Rh6 h4 Ra6 Ra1 Bg4 Kf2 Ke6 a4 Ke5 Kg2 Rf6 Re1 d3 Rf1 Kd4 Rxf6 gxf6 Kf2 c6 a5 a6 Nb1 Kxe4 Ke1 Be2 Nd2+ Ke3 Nb1 f5 Nd2 h5 Nb1 Kf3 Nc3 Kxg3 Na4 f4 Nxc5 f3 Ne4+ Kf4 Nd6 c5 b4 cxb4 c5 b3 Nc4 Kg3",
                annotations = mapOf(
            25 to Pair("!", "Lasker desactiva el ímpetu atacante de Marshall."),
            50 to Pair("!", "Final de torres ganado gracias al peón pasado alejado.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "marshall_lasker_1907_c",
                plyBefore = 45,
                title = "El Dominio del Final",
                prompt = "¿Cómo asegura Lasker la victoria técnica en el final?",
                hint = "Activa tu rey hacia el centro del tablero.",
                solutionSan = "Kf7",
                explanation = "El rey negro se suma activamente al final asegurando la ventaja."
            )
            )
        )
    }

    val game33: BookGame by lazy {
        BookGame(
            id = "lasker_tarrasch_1908_m1",
            gameNumber = 33,
            title = "El Duelo Filosófico por el Campeonato",
            whitePlayer = "Emanuel Lasker",
            blackPlayer = "Siegbert Tarrasch",
            event = "Düsseldorf (Mundial 1908, 1ª)",
            year = "1908",
            result = "1-0",
            eco = "C68",
            openingName = "Apertura Española",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "El choque cumbre entre el dogmatismo clásico del Doctor Tarrasch y el pragmatismo psicológico de Lasker. Lasker desarmó el sistema de Tarrasch con sutilezas estratégicas que desconcertaron al retador.",
            strategicTheme = "Lucha psicológica y explotación de debilidades ocultas en la Española.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 a6 Bxc6 dxc6 d4 exd4 Qxd4 Qxd4 Nxd4 c5 Ne2 Bd7 b3 Bc6 f3 Be7 Bb2 Bf6 Bxf6 Nxf6 Nd2 O-O-O O-O-O Rd7 Nf4 Re8 Nc4 b6 a4 a5 Rxd7 Nxd7 Rd1 Ne5 Nxe5 Rxe5 c4 Re8 Nh5 Rg8 Rd3 f6 Kd2 Be8 Ng3 Bd7 Ke3 Re8 Nh5 Re7 g4 c6 h4 Kc7 g5 f5 Ng3 fxe4 Nxe4 Bf5 h5 Rd7 Rc3 Rd1 Kf4 Bd7 Re3 Rh1 Ng3 Rh4+ Ke5 Rh3 f4 Kd8 f5 Rh4 f6 gxf6+ Kxf6 Be8 Nf5 Rf4 g6 hxg6 hxg6 Rg4 Rxe8+ Kxe8 g7 Kd7 Nh4 Rxg7 Kxg7 Ke6 Nf3 Kf5 Kf7 Ke4 Ke6 Kd3 Kd6 Kc3 Kxc6 Kxb3 Kb5",
                annotations = mapOf(
            25 to Pair("!", "Lasker provoca concesiones en la estructura de peones de Tarrasch."),
            50 to Pair("!", "Victoria categórica que marcó el rumbo del match.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "lasker_tarrasch_1908_c",
                plyBefore = 76,
                title = "Ruptura Decisiva",
                prompt = "¿Cómo quiebra Lasker la defensa de peones de Tarrasch?",
                hint = "Avanza el peón para abrir la columna al asalto.",
                solutionSan = "f4",
                explanation = "La ruptura f4 desmantela la fortaleza de peones negros."
            )
            )
        )
    }

    val game34: BookGame by lazy {
        BookGame(
            id = "tarrasch_lasker_1908_m4",
            gameNumber = 34,
            title = "La Demolición Psicológica de Tarrasch",
            whitePlayer = "Siegbert Tarrasch",
            blackPlayer = "Emanuel Lasker",
            event = "Düsseldorf (Mundial 1908, 4ª)",
            year = "1908",
            result = "0-1",
            eco = "C65",
            openingName = "Apertura Española",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "Cuarta partida del match de 1908. Lasker aceptó una posición pasiva y aparentemente incómoda, atrayendo a Tarrasch hacia un ataque sobreextendido para luego castigarlo sin piedad.",
            strategicTheme = "La defensa como trampa psicológica y el contragolpe fulminante.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 Nf6 O-O d6 d4 Bd7 Nc3 Be7 Re1 exd4 Nxd4 Nxd4 Qxd4 Bxb5 Nxb5 O-O Bg5 h6 Bh4 Re8 Rad1 Nd7 Bxe7 Rxe7 Qc3 Re5 Nd4 Rc5 Qb3 Nb6 f4 Qf6 Qf3 Re8 c3 a5 b3 a4 b4 Rc4 g3 Rd8 Re3 c5 Nb5 cxb4 Rxd6 Rxd6 e5 Rxf4 gxf4 Qg6+ Kh1 Qb1+ Kg2 Rd2+ Re2 Qxa2 Rxd2 Qxd2+ Kg3 a3 e6 Qe1+ Kg4 Qxe6+ f5 Qc4+ Nd4 a2 Qd1 Nd5 Qa4 Nxc3 Qe8+ Kh7 Kh5 a1=Q",
                annotations = mapOf(
            30 to Pair("!", "Lasker mantiene la calma bajo la presión aparente de Tarrasch."),
            60 to Pair("!", "Las piezas negras barren el tablero en el contragolpe.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "tarrasch_lasker_m4_c",
                plyBefore = 38,
                title = "El Contragolpe Inesperado",
                prompt = "Tarrasch ha avanzado demasiado sus peones. ¿Cómo asesta Lasker el golpe decisivo?",
                hint = "Infiltra tu dama en el corazón del flanco blanco.",
                solutionSan = "c3",
                explanation = "La dama negra centralizada ataca dos debilidades y decide la partida."
            )
            )
        )
    }

    val game35: BookGame by lazy {
        BookGame(
            id = "lasker_janowski_1909_m1",
            gameNumber = 35,
            title = "El Duelo de París",
            whitePlayer = "Emanuel Lasker",
            blackPlayer = "David Janowsky",
            event = "París (Match, 1ª)",
            year = "1909",
            result = "1-0",
            eco = "C77",
            openingName = "Apertura Española",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "En el match de París de 1909, Lasker neutralizó el estilo romántico y obstinado de Janowsky con una clase magistral de simplicidad y precisión en el contragolpe.",
            strategicTheme = "Simplificación favorable y castigo al juego demasiado optimista del rival.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 a6 Ba4 Nf6 O-O Be7 Re1 d6 Bxc6+ bxc6 d4 Nd7 dxe5 dxe5 Nbd2 f6 Nc4 a5 Bd2 a4 Na5 Ra6 Qe2 Nc5 Red1 Be6 b4 axb3 axb3 Qa8 Bc3 Kf7 Nh4 g6 Rf1 Kg7 f4 exf4 Nf5+ Bxf5 exf5 Bd6 Nc4 Rxa1 Rxa1 Re8 Qf3 Qd8 Qxc6 Ne4 Nxd6 Qxd6 Qxd6 cxd6 Bd4 gxf5 Ra2 Kf7 c3 Ke6 Kf1 Rc8 Ke1 Rb8 b4 Kd5 Ke2 Kc4 Ra6 Rg8 Kf3 Rg4 Ra5 Nd2+ Kf2 Ne4+ Kf3 Ng5+ Kf2 Ne4+ Kf3",
                annotations = mapOf(
            20 to Pair("!", "Control firme del centro evitando complicaciones."),
            40 to Pair("!", "Final de piezas pesadas con peón de ventaja.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "lasker_janowski_1909_c",
                plyBefore = 38,
                title = "Simplificación Favorable",
                prompt = "¿Cómo orienta Lasker la posición hacia un final técnicamente ganado?",
                hint = "Cambia las damas para desactivar el contrajuego negro.",
                solutionSan = "Rf1",
                explanation = "La centralización de dama fuerza el cambio y garantiza la victoria."
            )
            )
        )
    }

    val games: List<BookGame> by lazy {
        listOf(game25, game26, game27, game28, game29, game30, game31, game32, game33, game34, game35)
    }
}
