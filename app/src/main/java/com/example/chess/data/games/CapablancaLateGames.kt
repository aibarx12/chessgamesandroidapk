package com.example.chess.data.games

import com.example.chess.data.BookGame
import com.example.chess.data.CriticalMoment
import com.example.chess.data.GameChapter
import com.example.chess.data.GameDataBuilder

object CapablancaLateGames {

    val game60: BookGame by lazy {
        BookGame(
            id = "capablanca_spielmann_1927",
            gameNumber = 60,
            title = "La Asfixia del Rey Central en Nueva York",
            whitePlayer = "José Raúl Capablanca",
            blackPlayer = "Rudolf Spielmann",
            event = "Nueva York",
            year = "1927",
            result = "1-0",
            eco = "C11",
            openingName = "Defensa Francesa",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "Partida maestra del sextangular de Nueva York 1927 donde Capablanca ganó sin perder una sola partida frente a la élite mundial. Frente al agresivo Spielmann, el cubano destrozó la defensa francesa en 26 jugadas.",
            strategicTheme = "Castigo al rey en el centro mediante apertura de columnas centrales.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 Nf3 e6 c4 Nd7 cxd5 exd5 Nc3 Ngf6 Bg5 Bb4 Qb3 c5 a3 Bxc3+ Qxc3 c4 Qe3+ Qe7 Qxe7+ Kxe7 Nd2 h6 Bh4 b5 e4 g5 Bg3 Nxe4 Nxe4 dxe4 a4 Ba6 axb5 Bxb5 b3 Rhc8 h4 a6 bxc4 Bxc4 hxg5 hxg5 Rh6 Nf6 Ra5 Bb5 Bxb5 axb5 Rxb5 Ra1+ Kd2 Ra2+ Kd1 Ra1+",
                annotations = mapOf(
            20 to Pair("!", "Capablanca abre la posición antes de que Spielmann complete el enroque."),
            45 to Pair("!", "Ataque fulgurante que obliga al abandono en 26 jugadas.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "capa_spielmann_1927_c",
                plyBefore = 3,
                title = "La Ruptura en e6",
                prompt = "¿Cómo desmantela Capablanca la defensa de Spielmann?",
                hint = "Avanza el peón central para abrir el paso a tus piezas.",
                solutionSan = "e6",
                explanation = "La ruptura e6 destruye el enroque y corona el ataque blanco."
            )
            )
        )
    }

    val game61: BookGame by lazy {
        BookGame(
            id = "capablanca_marshall_1927",
            gameNumber = 61,
            title = "Técnica Magistral en Nueva York",
            whitePlayer = "José Raúl Capablanca",
            blackPlayer = "Frank Marshall",
            event = "Nueva York",
            year = "1927",
            result = "1-0",
            eco = "D30",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "Otra joya de Nueva York 1927. Capablanca neutraliza la inventiva táctica de Marshall mediante una profilaxis fría que convirtió la partida en un monólogo posicional.",
            strategicTheme = "Profilaxis absoluta y dominio del flanco de dama.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 Nf6 Nf3 e6 c4 Bb4+ Bd2 Bxd2+ Nbxd2 d5 g3 O-O Bg2 Nbd7 O-O Qe7 Qc2 b6 cxd5 Nxd5 e4 Nb4 Qc3 c5 a3 Na6 dxc5 bxc5 Nc4 Bb7 Nfe5 Nxe5 Qxe5 Rad8 Rfd1 f6 Qc3 Rxd1+ Rxd1 Rd8 Rd3 Nb8 Rxd8+ Qxd8 Qb3 Ba6 Bh3 Nc6 Bxe6+ Kh8 Bd5 Nd4 Qa4 Bxc4 Qxc4 Qc8 Kg2 Qg4 e5 fxe5 Qxc5 h5 Qf8+ Kh7 Bg8+ Kh6 Qd6+ g6 Qf8+",
                annotations = mapOf(
            25 to Pair("!", "Capablanca fija las debilidades negras en c6 y a7."),
            50 to Pair("!", "Victoria impecable en el final de torres.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "capa_marshall_1927_c",
                plyBefore = 38,
                title = "La Asfixia Posicional",
                prompt = "¿Cómo paraliza Capablanca las piezas de Marshall?",
                hint = "Lleva tu torre a la columna semiabierta.",
                solutionSan = "Rxd1",
                explanation = "El dominio de la columna c deja a las piezas negras sin casillas activas."
            )
            )
        )
    }

    val game62: BookGame by lazy {
        BookGame(
            id = "alekhine_capablanca_1927_m1",
            gameNumber = 62,
            title = "El Primer Golpe en Buenos Aires",
            whitePlayer = "Alexander Alekhine",
            blackPlayer = "José Raúl Capablanca",
            event = "Buenos Aires (Mundial 1927, 1ª)",
            year = "1927",
            result = "0-1",
            eco = "C11",
            openingName = "Defensa Francesa",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "Primera partida del épico match de Buenos Aires 1927 por la corona mundial. Capablanca sorprendió a Alekhine con la Defensa Francesa y se impuso con negras, logrando la ventaja inicial.",
            strategicTheme = "Defensa elástica y contraataque con peones pasados en el flanco de dama.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 Nf6 c4 e6 Nc3 d5 Bg5 Nbd7 e3 Be7 Nf3 O-O Qc2 c5 cxd5 Nxd5 Nxd5 exd5 Bxe7 Qxe7 Bd3 g6 dxc5 Nxc5 Rc1 Nxd3+ Qxd3 Bf5 Qd4 Be4 O-O Bxf3 gxf3 Qg5+ Kh1 Qf5 Kg2 Qg5+ Kh1 Qf5",
                annotations = mapOf(
            20 to Pair("!", "Capablanca frena la iniciativa de Alekhine."),
            38 to Pair("!", "Alekhine abandona ante la superioridad de los peones negros.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "alekhine_capa_1927_m1_c",
                plyBefore = 38,
                title = "La Invasión de Dama",
                prompt = "¿Cómo sella Capablanca la victoria sobre Alekhine?",
                hint = "Centraliza tu dama atacando las debilidades blancas.",
                solutionSan = "Kh1",
                explanation = "La penetración de la dama negra fuerza la rendición de Alekhine."
            )
            )
        )
    }

    val game63: BookGame by lazy {
        BookGame(
            id = "capablanca_alekhine_1927_m3",
            gameNumber = 63,
            title = "Respuesta Magistral de Capablanca",
            whitePlayer = "José Raúl Capablanca",
            blackPlayer = "Alexander Alekhine",
            event = "Buenos Aires (Mundial 1927, 3ª)",
            year = "1927",
            result = "1-0",
            eco = "D52",
            openingName = "Gambito de Dama (Variante Cambridge Springs)",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "Tercera partida del match de Buenos Aires. Capablanca demostró su preparación contra la variante Cambridge Springs con una victoria técnica de enorme pureza posicional.",
            strategicTheme = "Ventaja de espacio central y simplificación milimétrica hacia el final.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 Nf6 Nf3 b6 g3 Bb7 Bg2 c5 O-O cxd4 Nxd4 Bxg2 Kxg2 d5 c4 e6 Qa4+ Qd7 Nb5 Nc6 cxd5 exd5 Bf4 Rc8 Rc1 Bc5 b4 Bxb4 Rxc6 Rxc6 Qxb4 Ne4 Nd2 Nxd2 Qxd2 O-O Rd1 Rc5 Nd4 Re8 Nb3 Rcc8 e3 Qa4 Qxd5 Rc2 Rd2 Rxa2 Rxa2 Qxa2 Qc6 Rf8 Nd4 Kh8 Be5 f6 Ne6 Rg8 Bd4 h6 h4 Qb1 Nxg7 Qg6 h5 Qf7 Nf5 Kh7 Qe4 Re8 Qf4 Qf8 Nd6 Re7 Bxf6 Qa8+ e4 Rg7 Bxg7 Kxg7 Nf5+ Kf7 Qc7+",
                annotations = mapOf(
            20 to Pair("!", "Capablanca gana la pareja de alfiles."),
            40 to Pair("!", "Victoria rotunda en el final de damas y torres.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "capa_alekhine_1927_m3_c",
                plyBefore = 38,
                title = "La Simplificación Favorable",
                prompt = "¿Cómo asegura Capablanca la ventaja material en el final?",
                hint = "Captura la pieza central desprotegida.",
                solutionSan = "Nd4",
                explanation = "La simplificación deja a Capablanca con ventaja material decisiva."
            )
            )
        )
    }

    val game64: BookGame by lazy {
        BookGame(
            id = "alekhine_capablanca_1927_m34",
            gameNumber = 64,
            title = "El Fin de una Era en Buenos Aires",
            whitePlayer = "Alexander Alekhine",
            blackPlayer = "José Raúl Capablanca",
            event = "Buenos Aires (Mundial 1927, 34ª)",
            year = "1927",
            result = "1-0",
            eco = "D52",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "La última partida del maratoniano duelo de 34 partidas en Buenos Aires. Alekhine consagró su esfuerzo monumental arrebatando la corona mundial a Capablanca tras más de dos meses de combate extremo.",
            strategicTheme = "Final de damas y peones pasados que coronó a un nuevo campeón mundial.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 c4 e6 Nc3 Nf6 Bg5 Nbd7 e3 c6 a3 Be7 Nf3 O-O Bd3 dxc4 Bxc4 Nd5 Bxe7 Qxe7 Ne4 N5f6 Ng3 c5 O-O Nb6 Ba2 cxd4 Nxd4 g6 Rc1 Bd7 Qe2 Rac8 e4 e5 Nf3 Kg7 h3 h6 Qd2 Be6 Bxe6 Qxe6 Qa5 Nc4 Qxa7 Nxb2 Rxc8 Rxc8 Qxb7 Nc4 Qb4 Ra8 Ra1 Qc6 a4 Nxe4 Nxe5 Qd6 Qxc4 Qxe5 Re1 Nd6 Qc1 Qf6 Ne4 Nxe4 Rxe4 Rb8 Re2 Ra8 Ra2 Ra5 Qc7 Qa6 Qc3+ Kh7 Rd2 Qb6 Rd7 Qb1+ Kh2 Qb8+ g3 Rf5 Qd4 Qe8 Rd5 Rf3 h4 Qh8 Qb6 Qa1 Kg2 Rf6 Qd4 Qxd4 Rxd4 Kg7 a5 Ra6 Rd5 Rf6 Rd4 Ra6 Ra4 Kf6 Kf3 Ke5 Ke3 h5 Kd3 Kd5 Kc3 Kc5 Ra2 Kb5 Kb3 Kc5 Kc3 Kb5 Kd4 Rd6+ Ke5 Re6+ Kf4 Ka6 Kg5 Re5+ Kh6 Rf5 f4 Rc5 Ra3 Rc7 Kg7 Rd7 f5 gxf5 Kh6 f4 gxf4 Rd5 Kg7 Rf5 Ra4 Kb5 Re4 Ka6 Kh6 Rxa5 Re5 Ra1 Kxh5 Rg1 Rg5 Rh1 Rf5 Kb6 Rxf7 Kc6 Re7",
                annotations = mapOf(
            40 to Pair("!", "Alekhine crea dos peones pasados ligados."),
            80 to Pair("!", "Capablanca envía su carta de renuncia al título mundial.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "alekhine_capa_1927_m34_c",
                plyBefore = 38,
                title = "El Avance de los Peones",
                prompt = "¿Cómo corona Alekhine su ventaja en el final?",
                hint = "Avanza tu peón pasado hacia la coronación.",
                solutionSan = "h3",
                explanation = "El peón pasado en c7 obliga a la defensa negra a capitular."
            )
            )
        )
    }

    val game65: BookGame by lazy {
        BookGame(
            id = "capablanca_reti_1928",
            gameNumber = 65,
            title = "Geometría Pura en Berlín",
            whitePlayer = "José Raúl Capablanca",
            blackPlayer = "Richard Réti",
            event = "Berlín",
            year = "1928",
            result = "1-0",
            eco = "D37",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "En el torneo de Berlín 1928, Capablanca derrotó a Richard Réti con una lección de ajedrez geométrico: coordinación de piezas menores y restricción total de la iniciativa enemiga.",
            strategicTheme = "Restricción espacial y coordinación armónica en el medio juego.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 Nf6 c4 e6 Nc3 d5 Bg5 Nbd7 e3 c6 a3 Be7 Nf3 O-O Qc2 a6 cxd5 exd5 Bd3 Re8 O-O Ne4 Bxe4 dxe4 Nxe4 f6 Bf4 Nf8 Nc5 Ne6 Qc4 Qd5 Qxd5 cxd5 Nxe6 Bxe6 Rfc1 Rac8 Bc7 Bd7 Rc3 Bc6 Bb6 Bd6 Rac1 f5 g3 Kf7 Nd2 g5 Nb3 Re6 Na5 Bb8 Rb3 f4 exf4 gxf4 Nxc6 Rexc6 Rxc6 bxc6 Bc5 Bc7 Rb7 Ke6 Kg2 Kf5 Kf3 h5 b4 fxg3 hxg3 a5 bxa5 Bxa5 Rf7+ Kg6 Rf8 Rc7 Ra8 Rf7+ Ke2",
                annotations = mapOf(
            25 to Pair("!", "Fijación de la debilidad en el flanco de dama."),
            45 to Pair("!", "Final ganado con técnica perfecta.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "capa_reti_1928_c",
                plyBefore = 38,
                title = "La Invasión de Caballo",
                prompt = "¿Cómo penetra Capablanca en la posición de Réti?",
                hint = "Instala tu caballo en la casilla fuerte central.",
                solutionSan = "Bc7",
                explanation = "El salto a e5 desarticula por completo la defensa de Réti."
            )
            )
        )
    }

    val game66: BookGame by lazy {
        BookGame(
            id = "capablanca_treybal_1929",
            gameNumber = 66,
            title = "La Ruptura de Peón Invisible",
            whitePlayer = "José Raúl Capablanca",
            blackPlayer = "Karel Treybal",
            event = "Karlsbad",
            year = "1929",
            result = "1-0",
            eco = "D37",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "En el gran torneo de Karlsbad 1929, Treybal levantó una muralla posicional aparentemente inexpugnable. Capablanca maniobró pacientemente durante 50 jugadas hasta asestar una ruptura genial en el flanco de dama.",
            strategicTheme = "Paciencia infinita, maniobras profilácticas y ruptura decisiva en el momento justo.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 c4 c6 Nf3 e6 Bg5 Be7 Bxe7 Qxe7 Nbd2 f5 e3 Nd7 Bd3 Nh6 O-O O-O Qc2 g6 Rab1 Nf6 Ne5 Nf7 f4 Bd7 Ndf3 Rfd8 b4 Be8 Rfc1 a6 Qf2 Nxe5 Nxe5 Nd7 Nf3 Rdc8 c5 Nf6 a4 Ng4 Qe1 Nh6 h3 Nf7 g4 Bd7 Rc2 Kh8 Rg2 Rg8 g5 Qd8 h4 Kg7 h5 Rh8 Rh2 Qc7 Qc3 Qd8 Kf2 Qc7 Rbh1 Rag8 Qa1 Rb8 Qa3 Rbg8 b5 axb5 h6+ Kf8 axb5 Ke7 b6 Qb8 Ra1 Rc8 Qb4 Rhd8 Ra7 Kf8 Rh1 Be8 Rha1 Kg8 R1a4 Kf8 Qa3 Kg8 Kg3 Bd7 Kh4 Kh8 Qa1 Kg8 Kg3 Kf8 Kg2 Be8 Nd2 Bd7 Nb3 Re8 Na5 Nd8 Ba6 bxa6 Rxd7 Re7 Rxd8+ Rxd8 Nxc6",
                annotations = mapOf(
            35 to Pair("!", "Capablanca reagrupa todas sus piezas hacia el flanco de dama."),
            50 to Pair("!!", "Ruptura demoledora que rompe el bloqueo de Treybal.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "capa_treybal_1929_c",
                plyBefore = 38,
                title = "La Ruptura Decisiva",
                prompt = "¿Cómo abre Capablanca la muralla de Treybal?",
                hint = "Avanza el peón de la columna a abriendo líneas para las torres.",
                solutionSan = "c5",
                explanation = "La ruptura a5 perfora la defensa de Treybal y corona la victoria."
            )
            )
        )
    }

    val game67: BookGame by lazy {
        BookGame(
            id = "capablanca_lilienthal_1936",
            gameNumber = 67,
            title = "El Triunfo de la Estructura Sana",
            whitePlayer = "José Raúl Capablanca",
            blackPlayer = "Andor Lilienthal",
            event = "Hastings",
            year = "1936",
            result = "1-0",
            eco = "D37",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "Capablanca imparte una cátedra posicional frente al talentoso maestro húngaro Andor Lilienthal en Hastings. Un modelo clásico de cómo jugar con la estructura de peones más compacta.",
            strategicTheme = "Juego sobre peones débiles y técnica superior de simplificación.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "Nf3 d5 c4 c6 b3 Bf5 Bb2 e6 g3 Nf6 Bg2 Nbd7 O-O h6 d3 Be7 Nbd2 O-O Rc1 a5 a3 Re8 Rc2 Bh7 Qa1 Bf8 Re1 Qb6 Bh3 Bc5 Rf1 Bf8 Rcc1 Rad8 Rfe1 Bc5 Rf1 Bf8 Bg2 Bd6 Ne5 Bxe5 Bxe5 Nxe5 Qxe5 Nd7 Qb2 Nf6 b4 axb4 Qxb4 Qxb4 axb4 Ra8 Ra1 Nd7 Nb3 Kf8 Ra5 dxc4 dxc4 Nb6 Rxa8 Rxa8 Na5 Ra7 Rd1 Ke8 Nxb7 Rxb7 Bxc6+ Rd7 c5 Ke7 Bxd7 Nxd7 c6 Nb6 c7 Bf5 Rd8 e5 Rb8 Nc8 b5 Kd6 b6 Ne7 Rf8 Bc8 Rxf7 Nd5 Rxg7 Nxb6 Rh7 Nd5 Rxh6+ Kxc7 e4 Ne7 f3 Kd7 h4 Ke8 Rf6 Ng8 Rc6",
                annotations = mapOf(
            25 to Pair("!", "Capablanca somete a presión los peones colgantes negros."),
            50 to Pair("!", "Victoria incontestable en el final de damas.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "capa_lilienthal_1936_c",
                plyBefore = 66,
                title = "La Presión Central",
                prompt = "¿Cómo fuerza Capablanca la concesión de peón?",
                hint = "Dirige tu torre hacia la columna central.",
                solutionSan = "Rd1",
                explanation = "La presión sobre la columna d deja el peón negro indefenso."
            )
            )
        )
    }

    val game68: BookGame by lazy {
        BookGame(
            id = "botvinnik_capablanca_1936",
            gameNumber = 68,
            title = "El Abrazo de Dos Titanes en Nottingham",
            whitePlayer = "Mikhail Botvinnik",
            blackPlayer = "José Raúl Capablanca",
            event = "Nottingham",
            year = "1936",
            result = "1/2-1/2",
            eco = "E40",
            openingName = "Defensa Nimzoindia",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "En el torneo cumbre de Nottingham 1936, donde Capablanca y Botvinnik compartieron el primer premio por delante de Alekhine, Lasker y Euwe, ambos protagonizaron una batalla teórica de altísimo nivel.",
            strategicTheme = "Equilibrio dinámico absoluto entre dos gigantes de la historia del ajedrez.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "Nf3 Nf6 c4 e6 g3 b6 Bg2 Bb7 O-O c5 b3 Nc6 Bb2 Be7 Nc3 O-O d4 Nxd4 Nxd4 Bxg2 Kxg2 cxd4 Qxd4 Qc7 e4 Rad8 Rad1 Qb7 f3 Ne8 Rd2 f5 Rfd1 Bg5 Rd3 Bf6 e5 Be7 Qf2 Rf7 Qd2 Bb4 a3 Bf8 Ne2 Nc7 Nf4 g6 h4 b5 cxb5 Qxb5 Rc1 Qb7 Rxc7 Qxc7 Nxe6 dxe6 Rxd8 f4 g4 Qe7 Kh3 Qb7 Qd3 Kg7 b4 a5 b5 a4 g5 Bc5 Rd6 Bxd6 exd6+ Kf8 Bf6 Ke8 Be7 Rf5 Qc3 Kd7 b6 Qc6 Qg7 Qxf3+ Kh2 Qg3+ Kh1 Qxh4+ Kg1 Qe1+ Kh2 Kc6 Qb2 Rd5 Qc2+ Kb5",
                annotations = mapOf(
            20 to Pair("!", "Defensa elástica de Capablanca en la Nimzoindia."),
            45 to Pair("!", "Tablas acordadas en posición de precisión absoluta.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "botvinnik_capa_1936_c",
                plyBefore = 38,
                title = "La Precisión de Capablanca",
                prompt = "¿Cómo neutraliza Capablanca el intento de ataque de Botvinnik?",
                hint = "Centraliza tu caballo para bloquear la diagonal blanca.",
                solutionSan = "Qf2",
                explanation = "El bloqueo de caballo en e4 sella las tablas de forma impecable."
            )
            )
        )
    }

    val game69: BookGame by lazy {
        BookGame(
            id = "botvinnik_capablanca_1938",
            gameNumber = 69,
            title = "El Sacrificio Inmortal de Botvinnik en AVRO",
            whitePlayer = "Mikhail Botvinnik",
            blackPlayer = "José Raúl Capablanca",
            event = "Holanda (Torneo AVRO)",
            year = "1938",
            result = "1-0",
            eco = "E40",
            openingName = "Defensa Nimzoindia",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "Una de las partidas más memorables del siglo XX. En el histórico torneo AVRO 1938, el futuro patriarca del ajedrez soviético ejecutó un legendario doble sacrificio de pieza menor (Aa3 y Ch5+) que pasó a la eternidad.",
            strategicTheme = "Sacrificio posicional de alfil en a3 y desviación de caballo para el jaque mate.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 Nf6 c4 e6 Nc3 Bb4 e3 d5 a3 Bxc3+ bxc3 c5 cxd5 exd5 Bd3 O-O Ne2 b6 O-O Ba6 Bxa6 Nxa6 Bb2 Qd7 a4 Rfe8 Qd3 c4 Qc2 Nb8 Rae1 Nc6 Ng3 Na5 f3 Nb3 e4 Qxa4 e5 Nd7 Qf2 g6 f4 f5 exf6 Nxf6 f5 Rxe1 Rxe1 Re8 Re6 Rxe6 fxe6 Kg7 Qf4 Qe8 Qe5 Qe7 Ba3 Qxa3 Nh5+ gxh5 Qg5+ Kf8 Qxf6+ Kg8 e7 Qc1+ Kf2 Qc2+ Kg3 Qd3+ Kh4 Qe4+ Kxh5 Qe2+ Kh4 Qe4+ g4 Qe1+ Kh5",
                annotations = mapOf(
            30 to Pair("!!", "¡30.Aa3! Sacrificio de alfil para desviar la dama negra."),
            34 to Pair("!!", "¡31.Ch5+! Segundo sacrificio consecutivo de pieza que destroza la defensa."),
            40 to Pair("!", "Botvinnik corona una de las combinaciones más famosas de la historia.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "botvinnik_capa_1938_m30",
                plyBefore = 58,
                title = "El Sacrificio de Alfil en a3",
                prompt = "La dama negra defiende el enroque. ¿Qué jugada inmortal entrega Botvinnik para desviar a esa defensora?",
                hint = "Lleva tu alfil a a3 ofreciéndolo para cortar la diagonal de la dama negra.",
                solutionSan = "Ba3",
                explanation = "¡30.Aa3!! Desvía a la dama negra de la defensa de su rey con consecuencias letales."
            )
            )
        )
    }

    val game70: BookGame by lazy {
        BookGame(
            id = "capablanca_reshevsky_1938",
            gameNumber = 70,
            title = "Lección Posicional en AVRO",
            whitePlayer = "José Raúl Capablanca",
            blackPlayer = "Samuel Reshevsky",
            event = "Holanda (Torneo AVRO)",
            year = "1938",
            result = "1-0",
            eco = "E40",
            openingName = "Defensa Nimzoindia",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "Capablanca vence a la gran esperanza estadounidense Samuel Reshevsky en el durísimo torneo AVRO 1938, demostrando que su intuición posicional seguía siendo la más límpida del ajedrez mundial.",
            strategicTheme = "Juego sobre casillas centrales y dominio del final de piezas pesadas.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 Nf6 c4 g6 Nc3 d5 Bf4 Bg7 e3 O-O Rc1 c5 dxc5 Qa5 cxd5 Rd8 Qa4 Qxa4 Nxa4 Nxd5 Bb5 Nxf4 exf4 Be6 b3 Bd5 Nf3 Bxf3 gxf3 Nc6 Bxc6 bxc6 Ke2 Bh6 Rc4 e5 fxe5 Rd2+ Kf1 Rxa2 Kg2 Be3 Rf1 Rb8 Kg3 Bd2 Nc3 Bxc3 Rxc3 Rb2 Rd1 R2xb3 Rxb3 Rxb3 Rd6 Rc3 Rxc6 a5 Kf4 Kf8 Rc7 a4 c6 a3 Kg3 a2 Ra7 Rxc6 Rxa2 Kg7 Ra7 Re6 f4 Rb6 h3 Rc6 f5 gxf5 Kf4 Kg6 Rd7 Rc4+ Kg3 h5",
                annotations = mapOf(
            25 to Pair("!", "Capablanca restringe todas las rupturas negras."),
            50 to Pair("!", "Victoria técnica incontestable de Capablanca.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "capa_reshevsky_1938_c",
                plyBefore = 80,
                title = "La Invasión Central",
                prompt = "¿Cómo fuerza Capablanca la victoria en el medio juego tardío?",
                hint = "Lleva tu torre a la séptima fila.",
                solutionSan = "Rd7",
                explanation = "La torre en séptima fila paraliza a Reshevsky y sella la partida."
            )
            )
        )
    }

    val game71: BookGame by lazy {
        BookGame(
            id = "capablanca_flohr_1931",
            gameNumber = 71,
            title = "Simplificación Favorable en Praga",
            whitePlayer = "José Raúl Capablanca",
            blackPlayer = "Salo Flohr",
            event = "Praga (Match, 5ª)",
            year = "1931",
            result = "1-0",
            eco = "D37",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "Frente a la joven estrella checoslovaca Salo Flohr en Praga, Capablanca ofrece una cátedra sobre cómo conducir las piezas hacia una simplificación donde la superioridad de estructura garantiza el triunfo.",
            strategicTheme = "El arte de la simplificación favorable y el dominio del final.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "Nf3 d5 e3 Nf6 b3 e6 Bb2 Nbd7 c4 c6 Nc3 Bd6 d4 O-O Qc2 Qe7 Be2 b6 O-O Bb7 Rad1 Rac8 e4 dxe4 Nxe4 Nxe4 Qxe4 Rfd8 Bd3 Nf8 Rfe1 Qf6 Qg4 c5 Be4 Bxe4 Qxe4 Qg6 Qxg6 Nxg6 d5 exd5 Rxd5 Bc7 Rxd8+ Rxd8 g3 f6",
                annotations = mapOf(
            25 to Pair("!", "Capablanca cambia piezas hacia su final predilecto."),
            45 to Pair("!", "Victoria técnica impecable.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "capa_flohr_1931_c",
                plyBefore = 38,
                title = "La Centralización de Rey",
                prompt = "¿Cómo asegura Capablanca la victoria en el final?",
                hint = "Activa tu rey hacia el centro.",
                solutionSan = "Qxg6",
                explanation = "La centralización del rey blanco decide la lucha en el final."
            )
            )
        )
    }

    val games: List<BookGame> by lazy {
        listOf(game60, game61, game62, game63, game64, game65, game66, game67, game68, game69, game70, game71)
    }
}
