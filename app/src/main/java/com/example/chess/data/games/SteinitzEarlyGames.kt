package com.example.chess.data.games

import com.example.chess.data.BookGame
import com.example.chess.data.CriticalMoment
import com.example.chess.data.GameChapter
import com.example.chess.data.GameDataBuilder

object SteinitzEarlyGames {

    val game11: BookGame by lazy {
        BookGame(
            id = "steinitz_mongredien_1862",
            gameNumber = 11,
            title = "El Nacimiento del Método Posicional",
            whitePlayer = "Wilhelm Steinitz",
            blackPlayer = "Augustus Mongredien",
            event = "Londres",
            year = "1862",
            result = "1-0",
            eco = "B01",
            openingName = "Defensa Escandinava",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "En el torneo de Londres de 1862, un joven Steinitz muestra las primeras semillas de su teoría: acumulación de pequeñas ventajas, control central y aprovechamiento metódico de las casillas débiles.",
            strategicTheme = "Presión sobre casillas débiles y superioridad de desarrollo central.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 d5 exd5 Qxd5 Nc3 Qd8 d4 e6 Nf3 Nf6 Bd3 Be7 O-O O-O Be3 b6 Ne5 Bb7 f4 Nbd7 Qe2 Nd5 Nxd5 exd5 Rf3 f5 Rh3 g6 g4 fxg4 Rxh7 Nxe5 fxe5 Kxh7 Qxg4 Rg8 Qh5+ Kg7 Qh6+ Kf7 Qh7+ Ke6 Qh3+ Kf7 Rf1+ Ke8 Qe6 Rg7 Bg5 Qd7 Bxg6+ Rxg6 Qxg6+ Kd8 Rf8+ Qe8 Qxe8#",
                annotations = mapOf(
            20 to Pair("!", "Apertura de la columna central para las torres."),
            40 to Pair("!", "Maniobra de ruptura decisiva.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "steinitz_mong_m20",
                plyBefore = 7,
                title = "Ruptura Posicional",
                prompt = "¿Cómo convierte Steinitz su ventaja de espacio en ataque directo?",
                hint = "Avanza tu peón central para desestabilizar la defensa.",
                solutionSan = "e6",
                explanation = "La ruptura de peón quiebra la estructura negra y abre paso a las piezas blancas."
            )
            )
        )
    }

    val game12: BookGame by lazy {
        BookGame(
            id = "anderssen_steinitz_1866",
            gameNumber = 12,
            title = "La Conquista de la Corona Oficiosa",
            whitePlayer = "Adolf Anderssen",
            blackPlayer = "Wilhelm Steinitz",
            event = "Londres (Match, 13ª)",
            year = "1866",
            result = "0-1",
            eco = "C65",
            openingName = "Apertura Española (Defensa Berlinesa)",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "La victoria en este célebre match consagró a Steinitz como el mejor jugador del planeta tras el retiro de Morphy. Steinitz demostró la solidez impenetrable de la Defensa Berlinesa contra el ataque romántico.",
            strategicTheme = "Defensa pétrea y contragolpe fulgurante en el momento oportuno.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 Nf6 d3 d6 Bxc6+ bxc6 h3 g6 Nc3 Bg7 O-O O-O Bg5 h6 Be3 c5 Rb1 Ne8 b4 cxb4 Rxb4 c5 Ra4 Bd7 Ra3 f5 Qb1 Kh8 Qb7 a5 Rb1 a4 Qd5 Qc8 Rb6 Ra7 Kh2 f4 Bd2 g5 Qc4 Qd8 Rb1 Nf6 Kg1 Nh7 Kf1 h5 Ng1 g4 hxg4 hxg4 f3 Qh4 Nd1 Ng5 Be1 Qh2 d4 gxf3 gxf3 Nh3 Bf2 Nxg1 dxc5 Qh3+ Ke1 Nxf3+ Rxf3 Qxf3 Nc3 dxc5 Bxc5 Rc7 Nd5 Rxc5 Qxc5 Qxe4+ Kf2 Rc8 Nc7 Qe3+",
                annotations = mapOf(
            30 to Pair("!", "Maniobra elástica de caballo hacia el centro."),
            60 to Pair("!", "Conversión técnica impecable en el final.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "steinitz_anderssen_m30",
                plyBefore = 38,
                title = "El Contragolpe Negro",
                prompt = "¿Cómo neutraliza Steinitz el intento de ataque blanco en el flanco de rey?",
                hint = "Centraliza tu torre en una columna clave.",
                solutionSan = "Rb6",
                explanation = "El dominio de la columna central neutraliza las amenazas blancas y asegura la ventaja."
            )
            )
        )
    }

    val game13: BookGame by lazy {
        BookGame(
            id = "steinitz_paulsen_1870",
            gameNumber = 13,
            title = "El Gambito Steinitz: El Rey Audaz",
            whitePlayer = "Wilhelm Steinitz",
            blackPlayer = "Louis Paulsen",
            event = "Baden-Baden",
            year = "1870",
            result = "1-0",
            eco = "C25",
            openingName = "Apertura Vienesa (Gambito Steinitz)",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "En el gran torneo de Baden-Baden de 1870, Steinitz presentó su revolucionaria idea de que el rey es una pieza fuerte capaz de defenderse a sí misma en el centro del tablero.",
            strategicTheme = "El rey activo en el centro soportando la tormenta para cosechar la ventaja posicional.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nc3 Nc6 f4 exf4 d4 Qh4+ Ke2 d6 Nf3 Bg4 Bxf4 O-O-O Ke3 Qh5 Be2 Qa5 a3 Bxf3 Kxf3 Qh5+ Ke3 Qh4 b4 g5 Bg3 Qh6 b5 Nce7 Rf1 Nf6 Kf2 Ng6 Kg1 Qg7 Qd2 h6 a4 Rg8 b6 axb6 Rxf6 Qxf6 Bg4+ Kb8 Nd5 Qg7 a5 f5 axb6 cxb6 Nxb6 Ne7 exf5 Qf7 f6 Nc6 c4 Na7 Qa2 Nb5 Nd5 Qxd5 cxd5 Nxd4 Qa7+ Kc7 Rc1+ Nc6 Rxc6#",
                annotations = mapOf(
            10 to Pair("!", "El rey blanco se sitúa en e2 desafiando a las piezas negras."),
            35 to Pair("!", "Ataque blanco coordinado tras el fracaso del asedio negro.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "steinitz_paulsen_m18",
                plyBefore = 46,
                title = "Consolidación Central",
                prompt = "¿Cómo mantiene Steinitz el control del centro con su rey en e2?",
                hint = "Reagrupa el caballo hacia una casilla central inexpugnable.",
                solutionSan = "Nd5",
                explanation = "El caballo domina el centro y bloquea los contraataques negros."
            )
            )
        )
    }

    val game14: BookGame by lazy {
        BookGame(
            id = "blackburne_steinitz_1876",
            gameNumber = 14,
            title = "Dominio de Casillas Débiles en Londres",
            whitePlayer = "Joseph Henry Blackburne",
            blackPlayer = "Wilhelm Steinitz",
            event = "Londres (Match, 2ª)",
            year = "1876",
            result = "0-1",
            eco = "C45",
            openingName = "Apertura Escocesa",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "Steinitz arrasó a Blackburne por un aplastante 7-0 en este match. Una lección magistral sobre cómo explotar casillas débiles y convertir la pareja de alfiles en un arma mortal.",
            strategicTheme = "Pareja de alfiles en posiciones semiabiertas y asedio al flanco de rey.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 d4 exd4 Nxd4 Qh4 Nb5 Bb4+ Bd2 Qxe4+ Be2 Kd8 O-O Bxd2 Qxd2 a6 N1c3 Qe5 Na3 b5 Bf3 Nge7 Rad1 Qf5 Rfe1 Rb8 Qe2 d6 Ne4 Bd7 Qe3 f6 g4 Qg6 Nxd6 cxd6 Rxd6 Kc7 Bxc6 Nxc6 Qg3 Kc8 Red1 Rb7 Qg2 Nb8 R1d4 h5 Qd5 Qg5 Qxg5 fxg5 Rg6 Bxg4 Rxg5 Re8 Kg2 Rf7 h3 Bd7 Kg3 Re2 Rxh5 Rexf2 Rc5+ Nc6 Rd3 Kc7 Nb1 Kb6 Rcd5 Nb8 Nd2 Bc6 Ne4 Re2 Nc3 Rxc2 Rd2 Rxc3+ bxc3 Bxd5 Rxd5 Rc7 Rd3 Nc6 Kf4 Rf7+ Ke4 Rf2 a3 Ra2 c4 bxc4 Rg3 Rd2 Rxg7 Rd4+ Kf5 c3",
                annotations = mapOf(
            25 to Pair("!", "Los alfiles negros dominan ambas diagonales mayores."),
            50 to Pair("!", "Invasión decisiva de peones y piezas pesadas.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "blackburne_steinitz_m25",
                plyBefore = 38,
                title = "Apertura de la Posición",
                prompt = "¿Cómo activa Steinitz sus piezas menores sobre el rey blanco?",
                hint = "Avanza el peón para abrir líneas a tu alfil.",
                solutionSan = "Rxd6",
                explanation = "La ruptura central d5 abre todas las vías de asalto para los alfiles negros."
            )
            )
        )
    }

    val game15: BookGame by lazy {
        BookGame(
            id = "steinitz_sellman_1885",
            gameNumber = 15,
            title = "El Asedio Metódico a la Francesa",
            whitePlayer = "Wilhelm Steinitz",
            blackPlayer = "Alexander Sellman",
            event = "Baltimore",
            year = "1885",
            result = "1-0",
            eco = "C11",
            openingName = "Defensa Francesa",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "En una exhibición en Estados Unidos previa al primer campeonato del mundo, Steinitz ofrece un tratado modelo sobre el bloqueo y asedio a la cadena de peones de la Defensa Francesa.",
            strategicTheme = "Bloqueo posicional y ruptura enérgica en el flanco débil del adversario.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e6 d4 d5 Nc3 Nf6 e5 Nfd7 f4 c5 dxc5 Bxc5 Nf3 a6 Bd3 Nc6 Qe2 Nb4 Bd2 b5 Nd1 Nxd3+ cxd3 Qb6 b4 Be7 a3 f5 Rc1 Bb7 Be3 Qd8 Nd4 Nf8 O-O h5 Nc3 Kf7 Nb1 g6 Nd2 Nd7 N2b3 Rc8 Na5 Ba8 Rxc8 Qxc8 Rc1 Qb8 Qc2 Bd8 Nac6 Qb7 Nxd8+ Rxd8 Qc7 Qb8 Bf2 Qb6 Nf3 Qxc7 Rxc7 Ke8 Ng5 Nf8 Bc5 Nd7 Bd6",
                annotations = mapOf(
            20 to Pair("!", "Fijación de los peones centrales débiles de las negras."),
            45 to Pair("!", "Ruptura final con invasión de torres.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "steinitz_sellman_m22",
                plyBefore = 38,
                title = "La Invasión de la Columna Abierta",
                prompt = "¿Cómo penetran las piezas blancas en el campamento negro?",
                hint = "Dobla tus torres en la columna semiabierta.",
                solutionSan = "Nb1",
                explanation = "La torre en séptima fila asesta una parálisis absoluta a las negras."
            )
            )
        )
    }

    val game16: BookGame by lazy {
        BookGame(
            id = "zukertort_blackburne_1883",
            gameNumber = 16,
            title = "La Obra Maestra Combinativa de Zukertort",
            whitePlayer = "Johannes Zukertort",
            blackPlayer = "Joseph Henry Blackburne",
            event = "Londres",
            year = "1883",
            result = "1-0",
            eco = "A13",
            openingName = "Apertura Inglesa",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "La creación cumbre de Zukertort en el Torneo de Londres de 1883, considerado uno de los torneos más fuertes del siglo XIX. Zukertort sacrifica su dama en una combinación profunda y deslumbrante.",
            strategicTheme = "Ataque sobre la diagonal b1-h7 y sacrificio de dama para romper la defensa.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "c4 e6 e3 Nf6 Nf3 b6 Be2 Bb7 O-O d5 d4 Bd6 Nc3 O-O b3 Nbd7 Bb2 Qe7 Nb5 Ne4 Nxd6 cxd6 Nd2 Ndf6 f3 Nxd2 Qxd2 dxc4 Bxc4 d5 Bd3 Rfc8 Rae1 Rc7 e4 Rac8 e5 Ne8 f4 g6 Re3 f5 exf6 Nxf6 f5 Ne4 Bxe4 dxe4 fxg6 Rc2 gxh7+ Kh8 d5+ e5 Qb4 R8c5 Rf8+ Kxh7 Qxe4+ Kg7 Bxe5+ Kxf8 Bg7+ Kg8 Qxe7",
                annotations = mapOf(
            55 to Pair("!!", "¡28.Db4! La jugada tranquila de dama que desata la combinación."),
            61 to Pair("!!", "¡31.Axe7+! Remate artístico que obliga al abandono.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "zukertort_blackburne_m28",
                plyBefore = 54,
                title = "La Jugada Silenciosa de Dama",
                prompt = "Las negras creen haber bloqueado el ataque. ¿Cómo desmantela Zukertort la defensa?",
                hint = "Mueve la dama a b4 ofreciéndola tácticamente para desviar la torre.",
                solutionSan = "Qb4",
                explanation = "¡28.Db4! Jugada maestra que amenaza Dxe7 y paraliza todas las piezas negras."
            )
            )
        )
    }

    val game17: BookGame by lazy {
        BookGame(
            id = "zukertort_steinitz_1886_m9",
            gameNumber = 17,
            title = "El Primer Título Mundial: El Peón D Aislado",
            whitePlayer = "Johannes Zukertort",
            blackPlayer = "Wilhelm Steinitz",
            event = "San Luis (Mundial 1886, 9ª)",
            year = "1886",
            result = "0-1",
            eco = "D37",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "El momento de inflexión del Primer Campeonato del Mundo Oficial de 1886. Steinitz demostró la superioridad de su doctrina posicional al someter a un asedio científico al peón dama aislado de Zukertort.",
            strategicTheme = "Bloqueo y explotación del peón dama aislado mediante casillas de salto de caballo.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 c4 e6 Nc3 Nf6 Nf3 dxc4 e3 c5 Bxc4 cxd4 exd4 Be7 O-O O-O Qe2 Nbd7 Bb3 Nb6 Bf4 Nbd5 Bg3 Qa5 Rac1 Bd7 Ne5 Rfd8 Qf3 Be8 Rfe1 Rac8 Bh4 Nxc3 bxc3 Qc7 Qd3 Nd5 Bxe7 Qxe7 Bxd5 Rxd5 c4 Rdd8 Re3 Qd6 Rd1 f6 Rh3 h6 Ng4 Qf4 Ne3 Ba4 Rf3 Qd6 Rd2 Bc6 Rg3 f5 Rg6 Be4 Qb3 Kh7 c5 Rxc5 Rxe6 Rc1+ Nd1 Qf4 Qb2 Rb1 Qc3 Rc8 Rxe4 Qxe4",
                annotations = mapOf(
            20 to Pair("!", "Instalación del caballo en la casilla de bloqueo d5."),
            40 to Pair("!", "Presión implacable de piezas pesadas sobre el peón débil.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "steinitz_zukertort_m9_m25",
                plyBefore = 73,
                title = "El Bloqueo Perfecto",
                prompt = "¿Cómo intensifica Steinitz la presión sobre la debilidad central de Zukertort?",
                hint = "Dirige tu torre a la columna semiabierta c.",
                solutionSan = "Rc8",
                explanation = "El dominio de la columna c incrementa la parálisis posicional de las blancas."
            )
            )
        )
    }

    val games: List<BookGame> by lazy {
        listOf(game11, game12, game13, game14, game15, game16, game17)
    }
}
