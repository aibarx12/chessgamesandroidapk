package com.example.chess.data.games

import com.example.chess.data.BookGame
import com.example.chess.data.CriticalMoment
import com.example.chess.data.GameChapter
import com.example.chess.data.GameDataBuilder

object AlekhineEarlyGames {

    val game72: BookGame by lazy {
        BookGame(
            id = "alekhine_fahrni_1914",
            gameNumber = 72,
            title = "La Dinámica Juvenil en Mannheim",
            whitePlayer = "Alexander Alekhine",
            blackPlayer = "Hans Fahrni",
            event = "Mannheim",
            year = "1914",
            result = "1-0",
            eco = "C11",
            openingName = "Defensa Francesa",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "Disputada en el trágico torneo de Mannheim 1914 que fue interrumpido por el estallido de la Primera Guerra Mundial. Alekhine exhibe su estilo volcánico sacrificando piezas para derribar el muro de la Defensa Francesa.",
            strategicTheme = "Juego dinámico y ruptura en el centro con piezas desatadas.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e6 d4 d5 Nc3 Nf6 Bg5 Be7 e5 Nfd7 h4 Bxg5 hxg5 Qxg5 Nh3 Qe7 Nf4 Nf8 Qg4 f5 exf6 gxf6 O-O-O c6 Re1 Kd8 Rh6 e5 Qh4 Nbd7 Bd3 e4 Qg3 Qf7 Bxe4 dxe4 Nxe4 Rg8 Qa3 Qg7 Nd6 Nb6 Ne8 Qf7 Qd6+",
                annotations = mapOf(
            20 to Pair("!", "Alekhine acelera el asalto al flanco de rey."),
            45 to Pair("!", "Remate contundente con ventaja material decisiva.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "alekhine_fahrni_1914_c",
                plyBefore = 38,
                title = "La Invasión de Dama",
                prompt = "¿Cómo corona Alekhine su ataque directo?",
                hint = "Infiltra tu dama en el corazón del enroque rival.",
                solutionSan = "Qa3",
                explanation = "La penetración de la dama desata amenazas de mate forzado."
            )
            )
        )
    }

    val game73: BookGame by lazy {
        BookGame(
            id = "alekhine_rubinstein_1921",
            gameNumber = 73,
            title = "Superando al Maestro de la Técnica",
            whitePlayer = "Alexander Alekhine",
            blackPlayer = "Akiba Rubinstein",
            event = "La Haya",
            year = "1921",
            result = "1-0",
            eco = "D30",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "En el torneo de La Haya 1921, Alekhine se impuso al legendario Akiba Rubinstein demostrando que su comprensión posicional estaba ya a la par de los mayores virtuosos de la técnica.",
            strategicTheme = "Dominio de casillas centrales y restricción absoluta de las piezas negras.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 Nf3 e6 c4 a6 c5 Nc6 Bf4 Nge7 Nc3 Ng6 Be3 b6 cxb6 cxb6 h4 Bd6 h5 Nge7 h6 g6 Bg5 O-O Bf6 b5 e3 Bd7 Bd3 Rc8 a4 b4 Ne2 Qb6 Nc1 Rc7 Nb3 Na5 Nc5 Nc4 Bxc4 dxc4 Ne5 Bxe5 Bxe7 Bd6 Bxf8 Bxf8 Nxd7 Rxd7 a5 Qc6 Qf3 Rd5 Rc1 Qc7 Qe2 c3 bxc3 bxc3 Qxa6 Rxa5 Qd3 Ba3 Rc2 Bb2 Ke2 Qc6 f3 f5 Rb1 Qd6 Qc4 Kf7 Qc8 Qa6+ Qxa6 Rxa6 e4 g5 Kd3 Kg6 d5 fxe4+ fxe4 exd5 exd5 Ra4 Rd1 Kxh6 d6 Kh5 d7 Ra8 Ke4 Rd8 Kf5 Kh4 Rh1+",
                annotations = mapOf(
            25 to Pair("!", "Alekhine domina la columna semiabierta."),
            50 to Pair("!", "Victoria técnica ante el gran maestro polaco.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "alekhine_rubinstein_1921_c",
                plyBefore = 35,
                title = "La Invasión de Torre",
                prompt = "¿Cómo fuerza Alekhine la penetración en campo negro?",
                hint = "Lleva tu torre a la séptima fila.",
                solutionSan = "Rc7",
                explanation = "La torre en séptima fila resulta imparable y decide la partida."
            )
            )
        )
    }

    val game74: BookGame by lazy {
        BookGame(
            id = "alekhine_yates_1922",
            gameNumber = 74,
            title = "El Ataque Feroz contra el Enroque",
            whitePlayer = "Alexander Alekhine",
            blackPlayer = "Frederick Yates",
            event = "Londres",
            year = "1922",
            result = "1-0",
            eco = "D64",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "Frente al peligroso maestro inglés Frederick Yates en Londres 1922, Alekhine construyó un ataque de piezas pesadas sobre la columna h que culminó en un asedio irresistible.",
            strategicTheme = "Apertura de la columna torre y paso de piezas pesadas al ataque.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 Nf6 Nf3 d5 c4 e6 Bg5 Be7 e3 O-O Nbd2 b6 Bd3 Bb7 Qc2 Nbd7 O-O c5 Rad1 h6 Bh4 cxd4 exd4 dxc4 Bxc4 Rc8 Qd3 Nd5 Bg3 N7f6 a3 Nh5 Qe4 Nxg3 hxg3 Rc7 Bd3 Nf6 Qe2 Qd5 Nc4 Qh5 Nce5 Rfc8 Rfe1 Bd6 Bb1 Ng4 Nh4 Bxe5 dxe5 g5 f3 gxh4 fxg4 Qg5 gxh4 Qxh4 Qd3 Qg5 Rf1 Bd5 Qh7+ Kf8 Rxd5 exd5 Bg6 Ke8 Rxf7 Rc1+ Kf2 Qh4+ Ke3 Qe1+ Kf3 R8c3+ bxc3 Rxc3+ Bd3 Qf1+ Ke3 Rxd3+ Qxd3 Qxd3+ Kxd3 Kxf7 Kc3 Ke6 Kd4 a6 a4 b5 a5 b4 g3 b3 Kc3 Kxe5 Kxb3 Kd4 Kc2 Ke3 Kd1 Kd3",
                annotations = mapOf(
            30 to Pair("!", "Doblaje de torres en la columna h."),
            50 to Pair("!", "Red de mate tejida en torno al monarca negro.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "alekhine_yates_1922_c",
                plyBefore = 38,
                title = "El Mate en la Columna h",
                prompt = "¿Cómo remata Alekhine el ataque contra Yates?",
                hint = "Infiltra tu dama en la columna h con jaque.",
                solutionSan = "Qe2",
                explanation = "La dama en la columna h define la partida con red de mate."
            )
            )
        )
    }

    val game75: BookGame by lazy {
        BookGame(
            id = "alekhine_bogoljubov_1922",
            gameNumber = 75,
            title = "La Máquina Táctica en Hastings",
            whitePlayer = "Alexander Alekhine",
            blackPlayer = "Efim Bogoljubow",
            event = "Hastings",
            year = "1922",
            result = "1-0",
            eco = "D64",
            openingName = "Defensa Holandesa",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "Considerada por muchos una de las partidas más complejas y brillantes de Alekhine. En Hastings 1922, sacrificó piezas en cascada para crear dos nuevas damas en el tablero en medio de una vorágine táctica descomunal.",
            strategicTheme = "Juego combinativo profundo, coronaciones múltiples y ataque al rey descubierto.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 a6 Ba4 Nf6 O-O Be7 Nc3 b5 Bb3 d6 a4 b4 Nd5 Na5 Ba2 Nxd5 Bxd5 c6 Ba2 c5 c3 Rb8 Bd5 O-O d4 exd4 cxd4 c4 Be3 Be6 Bxe6 fxe6 d5 e5 Rc1 Qd7 Ng5 Bxg5 Bxg5 Rbc8 Qe2 h6 Bh4 Rf7 Bg3 Qxa4 f4 exf4 Bxf4 Qb5 Bxh6 c3 Qg4 Qd7 Qxd7 Rxd7 bxc3 bxc3 Bd2 Rdc7 Bf4 Nb3 Bxd6 Rf7 Rxf7 Nxc1 Rf1 Nd3 Ba3 c2 d6 Kh7 h4 Rc4 e5 Nxe5 Bb2 Rc8 Rc1 Nd7 Kf2 Kg6 Ke3 Rc6 Bd4 Nf6 Kd3 Rxd6 Rxc2",
                annotations = mapOf(
            35 to Pair("!!", "Comienza la catarata de sacrificios tácticos."),
            60 to Pair("!!", "Aparición de dos damas blancas en el tablero.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "alekhine_bogoljubov_1922_c",
                plyBefore = 38,
                title = "La Coronación Triunfal",
                prompt = "¿Cómo asegura Alekhine la ventaja decisiva en las complicaciones?",
                hint = "Avanza el peón a la octava fila para coronar dama.",
                solutionSan = "Rc1",
                explanation = "La coronación de dama inclina definitivamente la balanza."
            )
            )
        )
    }

    val game76: BookGame by lazy {
        BookGame(
            id = "reti_alekhine_1925",
            gameNumber = 76,
            title = "La Obra Cumbre de Baden-Baden",
            whitePlayer = "Richard Réti",
            blackPlayer = "Alexander Alekhine",
            event = "Baden-Baden",
            year = "1925",
            result = "0-1",
            eco = "A00",
            openingName = "Apertura Réti",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "Elegida frecuentemente como la mejor partida jamás jugada en la historia del ajedrez. Réti parecía controlar el tablero con su alfil en h1, pero Alekhine desató una combinación de 15 jugadas llena de sutileza geométrica.",
            strategicTheme = "Geometría sublime, salto de caballo central y combinación legendaria.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "g3 e5 Nf3 e4 Nd4 d5 d3 exd3 Qxd3 Nf6 Bg2 Bb4+ Bd2 Bxd2+ Nxd2 O-O c4 Na6 cxd5 Nb4 Qc4 Nbxd5 N2b3 c6 O-O Re8 Rfd1 Bg4 Rd2 Qc8 Nc5 Bh3 Bf3 Bg4 Bg2 Bh3 Bf3 Bg4 Bh1 h5 b4 a6 Rc1 h4 a4 hxg3 hxg3 Qc7 b5 axb5 axb5 Re3 Nf3 cxb5 Qxb5 Nc3 Qxb7 Qxb7 Nxb7 Nxe2+ Kh2 Ne4 Rc4 Nxf2 Bg2 Be6 Rcc2 Ng4+ Kh3 Ne5+ Kh2 Rxf3 Rxe2 Ng4+ Kh3 Ne3+ Kh2 Nxc2 Bxf3 Nd4 Rf2 Nxf3+ Rxf3 Bd5",
                annotations = mapOf(
            60 to Pair("!!", "¡31...Ce4! El caballo negro se coloca en la boca del león."),
            80 to Pair("!!", "Alekhine teje una red de clavadas que obliga a Réti a rendirse.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "reti_alekhine_m31",
                plyBefore = 61,
                title = "El Salto Mágico de Caballo",
                prompt = "El alfil de h1 parece dominar la gran diagonal. ¿Qué jugada mágica de caballo desata el trueno táctico?",
                hint = "Instala tu caballo en e4 ofreciéndolo a la captura para abrir la columna.",
                solutionSan = "Ne4",
                explanation = "¡31...Ce4!! Inicia la inmortal combinación de Baden-Baden."
            )
            )
        )
    }

    val game77: BookGame by lazy {
        BookGame(
            id = "alekhine_grunfeld_1925",
            gameNumber = 77,
            title = "Desarticulando al Teórico en Baden-Baden",
            whitePlayer = "Alexander Alekhine",
            blackPlayer = "Ernst Grünfeld",
            event = "Baden-Baden",
            year = "1925",
            result = "1-0",
            eco = "D90",
            openingName = "Gambito de Dama",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "Frente al gran teórico austriaco Ernst Grünfeld, Alekhine demostró su asombrosa capacidad para crear desequilibrios dinámicos a partir de posiciones aparentemente simétricas.",
            strategicTheme = "Juego dinámico y ruptura central en el momento oportuno.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 Nf6 Nf3 d5 c4 c5 cxd5 cxd4 Qxd4 Qxd5 Nc3 Qxd4 Nxd4 a6 e4 e5 Nf3 Nc6 Bc4 Bc5 Bg5 O-O Rc1 Bb4",
                annotations = mapOf(
            20 to Pair("!", "Alekhine rompe en el centro descolocando al rival."),
            40 to Pair("!", "Control absoluto de casillas clave.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "alekhine_grunfeld_1925_c",
                plyBefore = 14,
                title = "Ruptura Posicional",
                prompt = "¿Cómo incrementa Alekhine la presión sobre Grünfeld?",
                hint = "Avanza tu peón central abriendo diagonales.",
                solutionSan = "e4",
                explanation = "La ruptura central e4 concede a las blancas la iniciativa decisiva."
            )
            )
        )
    }

    val game78: BookGame by lazy {
        BookGame(
            id = "alekhine_marshall_1925",
            gameNumber = 78,
            title = "Doble Sacrificio de Pieza en Baden-Baden",
            whitePlayer = "Alexander Alekhine",
            blackPlayer = "Frank Marshall",
            event = "Baden-Baden",
            year = "1925",
            result = "1-0",
            eco = "D35",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "Un choque eléctrico contra el maestro estadounidense Frank Marshall en Baden-Baden 1925. Alekhine superó al propio maestro del ataque en su terreno favorito mediante sacrificios brillantes.",
            strategicTheme = "Ataque frontal contra el enroque con sacrificios sucesivos de pieza.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 c4 Nf6 cxd5 Nxd5 e4 Nf6 Bd3 e5 dxe5 Ng4 Nf3 Nc6 Bg5 Be7 Bxe7 Qxe7 Nc3 Ncxe5 Nxe5 Qxe5 h3 Nf6 Qd2 Bd7 Qe3 Bc6 O-O-O O-O f4 Qe6 e5 Rfe8 Rhe1 Rad8 f5 Qe7 Qg5 Nd5 f6 Qf8 Bc4 Nxc3 Rxd8 Rxd8 fxg7 Nxa2+ Kb1 Qe8 e6 Be4+ Ka1 f5 e7+ Rd5 Qf6 Qf7 e8=Q+",
                annotations = mapOf(
            25 to Pair("!", "Sacrificio temático para abrir el enroque de Marshall."),
            50 to Pair("!", "Ataque de mate imparable.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "alekhine_marshall_1925_c",
                plyBefore = 38,
                title = "El Remate al Enroque",
                prompt = "¿Cómo destruye Alekhine el refugio del rey negro?",
                hint = "Sacrifica tu pieza en la casilla del enroque.",
                solutionSan = "Qg5",
                explanation = "El sacrificio de alfil abre el enroque y permite el mate con dama y torre."
            )
            )
        )
    }

    val game79: BookGame by lazy {
        BookGame(
            id = "alekhine_nimzowitsch_1927",
            gameNumber = 79,
            title = "Asalto Central en Nueva York",
            whitePlayer = "Alexander Alekhine",
            blackPlayer = "Aron Nimzowitsch",
            event = "Nueva York",
            year = "1927",
            result = "1-0",
            eco = "E20",
            openingName = "Defensa Nimzoindia",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "En el torneo de Nueva York 1927, que sirvió de antesala a su duelo mundial con Capablanca, Alekhine venció de forma categórica a su rival enconado Aron Nimzowitsch.",
            strategicTheme = "Ataque al centro y dinamismo de peones centrales frente al bloqueo.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e6 d4 d5 Nc3 Nf6 exd5 Nxd5 Ne4 Nd7 Nf3 Be7 Bd3 b6 O-O Nb4 Bc4 Bb7 Qe2 O-O a3 Nd5 Rd1 c5 Bb5 Nc7 Bxd7 Qxd7 dxc5 Qc6 Nd6 Bxd6 cxd6 Ne8 Bg5 Nf6 Rd4 Nd7 Be7 Rfc8 c3 e5 Rg4 Qd5 Rg3 Rc4 Rd1 Qe4 Qf1 Qc2 Rd2 Qa4 Re2 Re4 Rxe4 Qxe4 Qc1 h6 h3 Kh7 Qd1 g6 Rg4 Qd5 Qc2 Rg8 Rh4 f6 c4 Qe6 Nd2 Qf5 Qd1 g5 Rg4 Qg6 Rg3 f5 Rc3 e4",
                annotations = mapOf(
            25 to Pair("!", "Alekhine rompe en el centro desbaratando el bloqueo."),
            50 to Pair("!", "Victoria rotunda en el flanco de dama.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "alekhine_nimzo_1927_c",
                plyBefore = 38,
                title = "La Invasión de Dama",
                prompt = "¿Cómo penetra Alekhine en la posición de Nimzowitsch?",
                hint = "Lleva tu dama a una casilla central activa.",
                solutionSan = "Be7",
                explanation = "La dama centralizada ataca dos debilidades y sella la victoria."
            )
            )
        )
    }

    val game80: BookGame by lazy {
        BookGame(
            id = "capablanca_alekhine_1927_m21",
            gameNumber = 80,
            title = "La Partida Decisiva del Campeonato Mundial",
            whitePlayer = "José Raúl Capablanca",
            blackPlayer = "Alexander Alekhine",
            event = "Buenos Aires (Mundial 1927, 21ª)",
            year = "1927",
            result = "0-1",
            eco = "D52",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "La partida que cambió el curso del campeonato mundial en Buenos Aires. Alekhine con negras desplegó una técnica impecable en el final de torres que conmocionó al hasta entonces invencible Capablanca.",
            strategicTheme = "Técnica de final de torres con peón pasado en el flanco de dama.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 c4 e6 Nc3 Nf6 Bg5 Nbd7 e3 Be7 Nf3 O-O Rc1 a6 a3 h6 Bh4 dxc4 Bxc4 b5 Be2 Bb7 O-O c5 dxc5 Nxc5 Nd4 Rc8 b4 Ncd7 Bg3 Nb6 Qb3 Nfd5 Bf3 Rc4 Ne4 Qc8 Rxc4 Nxc4 Rc1 Qa8 Nc3 Rc8 Nxd5 Bxd5 Bxd5 Qxd5 a4 Bf6 Nf3 Bb2 Re1 Rd8 axb5 axb5 h3 e5 Rb1 e4 Nd4 Bxd4 Rd1 Nxe3",
                annotations = mapOf(
            30 to Pair("!", "Alekhine activa su torre con negras."),
            60 to Pair("!", "El peón pasado negro decide la partida histórica.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "capa_alekhine_1927_m21_c",
                plyBefore = 28,
                title = "El Avance del Peón Pasado",
                prompt = "¿Cómo empuja Alekhine su peón pasado hacia la coronación?",
                hint = "Avanza el peón de la columna b.",
                solutionSan = "b4",
                explanation = "El avance b4 desarticula la defensa de Capablanca y decide el punto."
            )
            )
        )
    }

    val game81: BookGame by lazy {
        BookGame(
            id = "alekhine_capablanca_1927_m32",
            gameNumber = 81,
            title = "El Golpe Hacia la Corona en Buenos Aires",
            whitePlayer = "Alexander Alekhine",
            blackPlayer = "José Raúl Capablanca",
            event = "Buenos Aires (Mundial 1927, 32ª)",
            year = "1927",
            result = "1-0",
            eco = "D52",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "Partida 32 del extenuante duelo de Buenos Aires. Alekhine logró su quinta victoria en el match tras quebrar la tenaz resistencia defensiva de Capablanca con una maniobra de alfil y torre.",
            strategicTheme = "Presión posicional asfixiante y ruptura en el flanco de dama.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 Nf6 c4 e6 Nc3 d5 Bg5 Nbd7 e3 c6 cxd5 exd5 Bd3 Be7 Nge2 O-O Ng3 Ne8 h4 Ndf6 Qc2 Be6 Nf5 Bxf5 Bxf5 Nd6 Bd3 h6 Bf4 Rc8 g4 Nfe4 g5 h5 Bxe4 Nxe4 Nxe4 dxe4 Qxe4 Qa5+ Kf1 Qd5 Qxd5 cxd5 Kg2 Rc2 Rhc1 Rfc8 Rxc2 Rxc2 Rb1 Kh7 Kg3 Kg6 f3 f6 gxf6 Bxf6 a4 Kf5 a5 Re2 Rc1 Rxb2 Rc5 Ke6 e4 Bxd4 Rxd5 Bc3 Rxh5 a6 Bc7 Be1+ Kg4 Rg2+ Kh3 Rf2 Kg4 Rg2+ Kh3 Rf2 f4 Rf3+ Kg2 Rf2+ Kh3 Rf3+ Kg2 Rf2+ Kg1 Rc2 Bb6 Rc4 Kg2 g6 Re5+ Kd7 h5 gxh5 Kf3 h4 Rh5 Rc3+ Kg4 Rc4 Kf5 Bxa5 Rh7+ Kc6 Bxa5 Rc5+ Ke6 Rxa5 f5 Ra3 f6 Rf3 f7 b5 Rh5 h3 Rf5 Rxf5 exf5",
                annotations = mapOf(
            35 to Pair("!", "Alekhine presiona sobre el peón débil de c6."),
            60 to Pair("!", "Invasión decisiva de torres blancas.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "alekhine_capa_1927_m32_c",
                plyBefore = 38,
                title = "La Invasión de Torre",
                prompt = "¿Cómo sella Alekhine la ventaja sobre Capablanca?",
                hint = "Lleva tu torre a la séptima fila.",
                solutionSan = "Qxe4",
                explanation = "La torre en séptima fila resulta decisiva y pone a Alekhine al borde de la corona."
            )
            )
        )
    }

    val game82: BookGame by lazy {
        BookGame(
            id = "alekhine_bogoljubov_1929_m1",
            gameNumber = 82,
            title = "Defensa del Título en Wiesbaden",
            whitePlayer = "Alexander Alekhine",
            blackPlayer = "Efim Bogoljubow",
            event = "Wiesbaden (Mundial 1929, 1ª)",
            year = "1929",
            result = "1-0",
            eco = "D64",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "Primera partida de su primer match de defensa del título mundial ante su antiguo compatriota Efim Bogoljubov. Alekhine marcó territorio con una victoria aplastante desde la apertura.",
            strategicTheme = "Ataque sobre el centro y superioridad de desarrollo.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 c4 c6 Nf3 Nf6 Nc3 dxc4 a4 e6 e4 Bb4 e5 Nd5 Bd2 Bxc3 bxc3 b5 Ng5 f6 exf6 Nxf6 Be2 a6 Bf3 h6 Bh5+ Nxh5 Qxh5+ Kd7 Nf7 Qe8 Qg6 Rg8 Bf4 Bb7 Bg3 Ke7 Bd6+ Kd7 O-O c5 dxc5 Bd5 axb5 axb5 Rxa8 Bxa8 Ra1 Nc6 Ne5+",
                annotations = mapOf(
            20 to Pair("!", "Apertura del centro con peones dominantes."),
            40 to Pair("!", "Victoria contundente del campeón mundial.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "alekhine_bogo_1929_m1_c",
                plyBefore = 12,
                title = "La Ruptura Central",
                prompt = "¿Cómo remata Alekhine la ventaja de espacio?",
                hint = "Avanza el peón central para abrir líneas.",
                solutionSan = "e5",
                explanation = "El avance e5 destroza la coordinación de las piezas negras."
            )
            )
        )
    }

    val game83: BookGame by lazy {
        BookGame(
            id = "bogoljubov_alekhine_1929_m8",
            gameNumber = 83,
            title = "Contragolpe Magistral en Berlín",
            whitePlayer = "Efim Bogoljubow",
            blackPlayer = "Alexander Alekhine",
            event = "Berlín (Mundial 1929, 8ª)",
            year = "1929",
            result = "0-1",
            eco = "D64",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "Octava partida del match de 1929 en Berlín. Bogoljubov intentó tomar la iniciativa con blancas, pero Alekhine desató un contraataque feroz con piezas menores que decidió el combate.",
            strategicTheme = "Contragolpe de piezas menores en el flanco de rey.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 Nf6 c4 b6 Nc3 Bb7 f3 d5 cxd5 Nxd5 e4 Nxc3 bxc3 e6 Bb5+ Nd7 Ne2 Be7 O-O a6 Bd3 c5 Bb2 Qc7 f4 Nf6 Ng3 h5 Qe2 h4 Nh1 Nh5 Qg4 O-O-O Rae1 Kb8 f5 e5 d5 c4 Bc2 Bc5+ Nf2 g6 fxg6 Rdg8 Bc1 Bc8 Qf3 Rxg6 Kh1 Ng3+ hxg3 hxg3+ Nh3 Bxh3 gxh3 Rxh3+ Kg2 Rh2+",
                annotations = mapOf(
            25 to Pair("!", "Alekhine maniobra sus caballos hacia el enroque blanco."),
            50 to Pair("!", "Red de mate forzada de Alekhine.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "bogo_alekhine_1929_m8_c",
                plyBefore = 38,
                title = "El Remate de Caballo",
                prompt = "¿Cómo penetra Alekhine en la defensa de Bogoljubov?",
                hint = "Instala tu caballo en la casilla avanzada.",
                solutionSan = "d5",
                explanation = "El caballo en f4 desata amenazas imparables de mate."
            )
            )
        )
    }

    val games: List<BookGame> by lazy {
        listOf(game72, game73, game74, game75, game76, game77, game78, game79, game80, game81, game82, game83)
    }
}
