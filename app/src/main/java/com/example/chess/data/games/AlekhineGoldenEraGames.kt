package com.example.chess.data.games

import com.example.chess.data.BookGame
import com.example.chess.data.CriticalMoment
import com.example.chess.data.GameChapter
import com.example.chess.data.GameDataBuilder

object AlekhineGoldenEraGames {

    val game84: BookGame by lazy {
        BookGame(
            id = "alekhine_nimzowitsch_1930",
            gameNumber = 84,
            title = "La Pistola de Alekhine en San Remo",
            whitePlayer = "Alexander Alekhine",
            blackPlayer = "Aron Nimzowitsch",
            event = "San Remo",
            year = "1930",
            result = "1-0",
            eco = "C17",
            openingName = "Defensa Francesa (Variante Winawer)",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "La cumbre artística de la carrera de Alekhine. En el torneo de San Remo 1930, que ganó con 14 puntos de 15 posibles, Alekhine triplicó sus piezas pesadas (Tf2, Tf3 y Df1) en la columna c abierta, inmortalizando la formación bautizada como \"La Pistola de Alekhine\".",
            strategicTheme = "Triplicación de piezas pesadas en una columna abierta y asfixia total por zugzwang.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e6 d4 d5 Nc3 Bb4 e5 c5 Bd2 Ne7 Nb5 Bxd2+ Qxd2 O-O c3 b6 f4 Ba6 Nf3 Qd7 a4 Nbc6 b4 cxb4 cxb4 Bb7 Nd6 f5 a5 Nc8 Nxb7 Qxb7 a6 Qf7 Bb5 N8e7 O-O h6 Rfc1 Rfc8 Rc2 Qe8 Rac1 Rab8 Qe3 Rc7 Rc3 Qd7 R1c2 Kf8 Qc1 Rbc8 Ba4 b5 Bxb5 Ke8 Ba4 Kd8 h4 h5 Kh2 g6 g3",
                annotations = mapOf(
            45 to Pair("!", "23.Tfc2! Comienza la alineación de las tres piezas pesadas."),
            51 to Pair("!!", "¡26.Dc1! La formación de la Pistola de Alekhine se completa."),
            60 to Pair("!!", "¡30.h4! Nimzowitsch queda en completo zugzwang y abandona.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "alekhine_gun_m26",
                plyBefore = 50,
                title = "La Formación de la Pistola",
                prompt = "Las torres blancas ya están dobladas en la columna c. ¿Cómo completa Alekhine la batería más temible del ajedrez?",
                hint = "Coloca tu dama detrás de ambas torres en la columna c.",
                solutionSan = "Qc1",
                explanation = "¡26.Dc1! La dama respalda a ambas torres creando una presión indestructible."
            )
            )
        )
    }

    val game85: BookGame by lazy {
        BookGame(
            id = "alekhine_vidmar_1931",
            gameNumber = 85,
            title = "Invasión por la Columna en Bled",
            whitePlayer = "Alexander Alekhine",
            blackPlayer = "Milan Vidmar",
            event = "Bled",
            year = "1931",
            result = "1-0",
            eco = "D37",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "En el torneo legendario de Bled 1931, donde Alekhine arrasó con 5.5 puntos de ventaja sobre el segundo clasificado sin perder una sola partida, derrotó a Milan Vidmar con una clase magistral de control de columnas abiertas.",
            strategicTheme = "Apertura e invasión de columnas abiertas y penetración en séptima fila.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 Nf3 Nf6 c4 c6 Nc3 e6 Bg5 Be7 e3 O-O Qc2 Ne4 Bxe7 Qxe7 Bd3 Nxc3 bxc3 Kh8 cxd5 exd5 O-O Bg4 Ne5 Bh5 Bxh7 g6 g4 Bxg4 Nxg4 Qg5 h3 Kxh7 f4 Qh4 Kh2 Nd7 Rab1 b6 Rg1 Nf6 Ne5 Ne4 Rbf1 Kg7 Rg4 Qh6 f5 Qxe3 Qg2 Qd2 f6+ Kg8 Nxc6 Qxg2+ Kxg2 Rfe8 Ne7+ Rxe7 fxe7 Re8 c4 Rxe7 cxd5 Nc3 d6 Rd7 Rc1 Nb5 Rg5 Nxd6 Rd5 Kf8 Re1 Rd8 Kf3 Rd7 Kf4 Kg7 Re8 Kf6 h4 Kg7 a4 Kf6 Rc8 Ke6 Re5+ Kf6 Rc6 Rd8 a5 b5 Rxb5 Ke6 Re5+ Kf6 Ra6 Rd7 Kg4 Rd8 Kf3 Rd7 Kf4 Rd8 h5 gxh5 Rxh5 Rd7 Re5 Rd8 Rxa7 Nc4 Ra6+ Kg7 Rg5+ Kf8 Ke4 Ke7 Rc5 Nd6+ Kd3 Ke6 Rcc6 Kd5 Rxd6+ Rxd6 Rxd6+ Kxd6 a6",
                annotations = mapOf(
            25 to Pair("!", "Alekhine dobla torres en la columna abierta d."),
            50 to Pair("!", "Penetración mortal que fuerza la rendición de Vidmar.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "alekhine_vidmar_1931_c",
                plyBefore = 67,
                title = "La Invasión en Séptima",
                prompt = "¿Cómo penetra Alekhine en la retaguardia negra?",
                hint = "Lleva tu torre a la séptima fila.",
                solutionSan = "Rd7",
                explanation = "La torre en d7 asfixia por completo la defensa de Vidmar."
            )
            )
        )
    }

    val game86: BookGame by lazy {
        BookGame(
            id = "alekhine_pirc_1931",
            gameNumber = 86,
            title = "Fuegos Artificiales en Bled",
            whitePlayer = "Alexander Alekhine",
            blackPlayer = "Vasja Pirc",
            event = "Bled",
            year = "1931",
            result = "1-0",
            eco = "D37",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "Frente al joven maestro yugoslavo Vasja Pirc, Alekhine ejecutó un asalto arrollador al enroque rival, combinando sacrificios de alfil y avances de peón para dar mate en el centro.",
            strategicTheme = "Ataque de mate al enroque mediante sacrificios de atracción.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 Nf6 c4 e6 Nf3 d5 e3 c6 Bd3 Nbd7 Nbd2 Be7 O-O O-O e4 dxc4 Nxc4 c5 Bc2 b5 Ne3 Bb7 e5 Nd5 Qd3 f5 exf6 N7xf6 Qxb5 Qc7 Bb3 Nf4 Nf5 N6d5 Bxd5 Nxd5 Nxe7+ Qxe7 Bg5 Nf6 Qxc5 Qe8 Bxf6 Rxf6 Ne5 Qh5 f3 Qg5 Ng4 Rf5 Qc1 Rf4 Qe3 Bd5 Ne5 Qf6 Rac1 h5 Rc7 Rf5 Rfc1 h4 Rc8+ Rxc8 Rxc8+ Kh7 b3 h3 g4 Rf4 Qd3+ g6 Rc7+ Kh6 Qe3 Bxf3",
                annotations = mapOf(
            20 to Pair("!", "Alekhine lanza sus peones al asalto del flanco de rey."),
            38 to Pair("!!", "Sacrificio fulgurante de pieza que abre la red de mate.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "alekhine_pirc_1931_c",
                plyBefore = 38,
                title = "El Remate en el Enroque",
                prompt = "¿Cómo destruye Alekhine la estructura defensiva de Pirc?",
                hint = "Sacrifica tu alfil en la casilla f7 o h7.",
                solutionSan = "Bg5",
                explanation = "El sacrificio en h7 desata el mate imparable con dama y torre."
            )
            )
        )
    }

    val game87: BookGame by lazy {
        BookGame(
            id = "alekhine_flohr_1931",
            gameNumber = 87,
            title = "Lección Posicional a la Joven Promesa",
            whitePlayer = "Alexander Alekhine",
            blackPlayer = "Salo Flohr",
            event = "Bled",
            year = "1931",
            result = "1-0",
            eco = "D37",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "Salo Flohr era considerado el prodigio más sólido y técnico de la nueva generación. En Bled 1931, Alekhine le impartió una severa lección posicional sobre el control del centro.",
            strategicTheme = "Dominio central y restricción de la actividad de las piezas rivales.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 c4 dxc4 Nf3 Nf6 e3 e6 Bxc4 c5 O-O Nc6 Qe2 a6 Rd1 b5 dxc5 Qc7 Bd3 Bxc5 a4 b4 Nbd2 O-O Nb3 Be7 e4 Nd7 Be3 Nde5 Nxe5 Nxe5 Rac1 Qb8 Bc5 Bxc5 Nxc5 Qb6 Qh5 Nd7 Be2 g6 Qg5 Nxc5 Rxc5 a5 h4 Ba6 Bf3 f6 Qe3 Rad8 Rxd8 Rxd8 e5 f5 Rc8",
                annotations = mapOf(
            25 to Pair("!", "Alekhine anula todo el contrajuego de Flohr."),
            45 to Pair("!", "Victoria técnica incontestable de Alekhine.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "alekhine_flohr_1931_c",
                plyBefore = 38,
                title = "La Invasión Central",
                prompt = "¿Cómo fuerza Alekhine la capitulación de Flohr?",
                hint = "Lleva tu torre a la casilla dominante en el centro.",
                solutionSan = "Qh5",
                explanation = "La torre en séptima fila decide la lucha de forma inmediata."
            )
            )
        )
    }

    val game88: BookGame by lazy {
        BookGame(
            id = "alekhine_bogoljubov_1934_m4",
            gameNumber = 88,
            title = "Segundo Duelo Mundial en Alemania",
            whitePlayer = "Alexander Alekhine",
            blackPlayer = "Efim Bogoljubow",
            event = "Alemania (Mundial 1934, 4ª)",
            year = "1934",
            result = "1-0",
            eco = "D64",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "Cuarta partida del segundo match mundial entre Alekhine y Bogoljubov en 1934. Alekhine reafirmó su superioridad táctica con un ataque arrollador que desbordó la defensa de su compatriota.",
            strategicTheme = "Ataque sobre el enroque con concentración masiva de piezas menores.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 c4 c6 Nc3 Nf6 e3 e6 Bd3 Nbd7 f4 dxc4 Bxc4 b5 Bd3 Bb7 Nf3 a6 a4 b4 Ne2 c5 O-O Be7 a5 O-O Ng3 g6 Qe2 cxd4 exd4 Nb8 Ne5 Nc6 Nxc6 Bxc6 Bc4 Bb7 Be3 Qd6 Rad1 Rfe8 b3 Bf8 Rd3 Qc7 Qa2 Bd6 Bd2 Qc6 Be1 Rad8 Rd2 Be7 Qb2 Rd7 Rc2 Qd6 Ne2 Nd5 Qc1 Bd8 Bg3 Qe7 Ra2 Qf6 Qd2 Qf5 Bd3 Qf6 Bc4 Be7 Qd3 Red8 Be1 Qf5 Qd2 Qe4 Bd3 Qe3+ Bf2 Qxd2 Rxd2 Rc8 Bc4 Kg7 g3 Rcd8 Rc1 h6 Bd3 f5 Rdc2 g5 g4 Nxf4 Nxf4 gxf4 gxf5 e5 Re1 exd4 Rxe7+ Rxe7 Bh4 Kf7 Bxe7 Kxe7 Rc7+ Rd7 f6+ Ke8 Bg6+ Kd8 f7 Kxc7 f8=Q f3 Qxb4 Rd6 Bd3",
                annotations = mapOf(
            30 to Pair("!", "Alekhine abre líneas hacia el rey de Bogoljubov."),
            60 to Pair("!", "Victoria rotunda del campeón del mundo.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "alekhine_bogo_1934_m4_c",
                plyBefore = 38,
                title = "La Invasión Final",
                prompt = "¿Cómo penetra Alekhine en la retaguardia negra?",
                hint = "Infiltra tu dama con jaque.",
                solutionSan = "Be3",
                explanation = "El jaque de dama fuerza la entrega de material decisivo."
            )
            )
        )
    }

    val game89: BookGame by lazy {
        BookGame(
            id = "euwe_alekhine_1935_m1",
            gameNumber = 89,
            title = "Apertura del Duelo Holandés de 1935",
            whitePlayer = "Max Euwe",
            blackPlayer = "Alexander Alekhine",
            event = "Holanda (Mundial 1935, 1ª)",
            year = "1935",
            result = "0-1",
            eco = "D37",
            openingName = "Defensa Eslava",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "Primera partida del Campeonato del Mundo de 1935 en Holanda. Alekhine comenzó el match con una victoria brillante con negras, demostrando su profundidad en la Defensa Eslava.",
            strategicTheme = "Contragolpe dinámico en el centro y explotación de la pareja de alfiles.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 c4 c6 Nf3 Nf6 e3 e6 Nc3 a6 c5 Nbd7 b4 a5 b5 Ne4 Nxe4 dxe4 Nd2 f5 f3 Qh4+ g3 Qh6 Qe2 Be7 Bg2 O-O O-O Nf6 Nc4 Bd8 fxe4 fxe4 Nd6 Qg6 b6 Be7 Nc4 Qg5 Bd2 e5 Nxe5 Be6 Rf4 Qh6 a3 g5 Rf2 Qg7 Raf1 h5 Kh1 Qh7 Bc3 h4 gxh4 Qxh4 Ng6 Qh7 Nxf8 Rxf8 d5 Nxd5 Rxf8+ Bxf8 Bd4 Be7 Qf2 Qh4 Qxh4 gxh4 Bxe4 Bd8 Bf5 Bxf5 Rxf5 Ne7 Rf6 Nc8 Rxc6",
                annotations = mapOf(
            25 to Pair("!", "Alekhine toma la iniciativa en el flanco de dama."),
            40 to Pair("!", "Euwe abandona ante la superioridad de los peones negros.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "euwe_alekhine_1935_m1_c",
                plyBefore = 46,
                title = "El Contragolpe de Alekhine",
                prompt = "¿Cómo corona Alekhine su ventaja en el final?",
                hint = "Avanza tu peón pasado en el flanco de dama.",
                solutionSan = "a3",
                explanation = "El avance del peón pasado asegura la coronación y la victoria."
            )
            )
        )
    }

    val game90: BookGame by lazy {
        BookGame(
            id = "euwe_alekhine_1935_m4",
            gameNumber = 90,
            title = "Contragolpe Táctico de Alekhine",
            whitePlayer = "Max Euwe",
            blackPlayer = "Alexander Alekhine",
            event = "Holanda (Mundial 1935, 4ª)",
            year = "1935",
            result = "0-1",
            eco = "D64",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "Cuarta partida del duelo de 1935. Alekhine amplió su ventaja inicial con una victoria electrizante con negras donde explotó las imprecisiones de Euwe en el medio juego.",
            strategicTheme = "Ataque sobre el centro desprotegido y sacrificio de atracción.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 Nf6 c4 g6 Nc3 d5 Qb3 dxc4 Qxc4 Bg7 Bf4 c6 Rd1 Qa5 Bd2 b5 Qb3 b4 Na4 Na6 e3 Be6 Qc2 O-O b3 Rab8 Bd3 Rfc8 Ne2 c5 Bxa6 Qxa6 Nxc5 Qb5 Nf4 Bg4 f3 e5 Nfd3 exd4 fxg4 dxe3 Bxe3 Nxg4 Bf4 Bc3+ Rd2 Rxc5 Nxc5 Qxc5 Bxb8 Qe7+ Kd1 Ne3+ Kc1 Nxc2 Rxc2 h5 Rd1 Bg7 h3 a5 Bf4 Qe4 Bc7 Qe3+ Kb1 a4 bxa4 b3 axb3 Qxb3+ Kc1 Bh6+ Rdd2 Qxa4 Be5 Kh7 Bc3 Qb5 Bd4 Qe2 g4 Qe1+ Kb2 Bxd2 Rc8 Bc1+",
                annotations = mapOf(
            20 to Pair("!", "Alekhine abre líneas en el flanco de dama."),
            40 to Pair("!", "Victoria decisiva con negras.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "euwe_alekhine_1935_m4_c",
                plyBefore = 38,
                title = "La Invasión de Dama",
                prompt = "¿Cómo penetra Alekhine en la posición de Euwe?",
                hint = "Lleva tu dama a la casilla central dominante.",
                solutionSan = "Nfd3",
                explanation = "La penetración de la dama negra sella la victoria."
            )
            )
        )
    }

    val game91: BookGame by lazy {
        BookGame(
            id = "euwe_alekhine_1935_m26",
            gameNumber = 91,
            title = "La Perla de Zandvoort: El Sacrificio de Euwe",
            whitePlayer = "Max Euwe",
            blackPlayer = "Alexander Alekhine",
            event = "Zandvoort (Mundial 1935, 26ª)",
            year = "1935",
            result = "1-0",
            eco = "D97",
            openingName = "Defensa Grünfeld",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "Bautizada por Tartakower como \"La Perla de Zandvoort\". En la partida más célebre del match de 1935, el doctor Max Euwe sacrificó una pieza menor para lanzar una ofensiva arrolladora de peones centrales que desmoronó la corona de Alekhine.",
            strategicTheme = "Sacrificio posicional de pieza por peones centrales masivos y ataque al enroque.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 e6 c4 f5 g3 Bb4+ Bd2 Be7 Bg2 Nf6 Nc3 O-O Nf3 Ne4 O-O b6 Qc2 Bb7 Ne5 Nxc3 Bxc3 Bxg2 Kxg2 Qc8 d5 d6 Nd3 e5 Kh1 c6 Qb3 Kh8 f4 e4 Nb4 c5 Nc2 Nd7 Ne3 Bf6 Nxf5 Bxc3 Nxd6 Qb8 Nxe4 Bf6 Nd2 g5 e4 gxf4 gxf4 Bd4 e5 Qe8 e6 Rg8 Nf3 Qg6 Rg1 Bxg1 Rxg1 Qf6 Ng5 Rg7 exd7 Rxd7 Qe3 Re7 Ne6 Rf8 Qe5 Qxe5 fxe5 Rf5 Re1 h6 Nd8 Rf2 e6 Rd2 Nc6 Re8 e7 b5 Nd8 Kg7 Nb7 Kf6 Re6+ Kg5 Nd6 Rxe7 Ne4+",
                annotations = mapOf(
            39 to Pair("!!", "¡20.Cxf7! Euwe desata el sacrificio inmortal de Zandvoort."),
            50 to Pair("!", "Los peones pasados centrales blancos barren el tablero.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "zandvoort_m20",
                plyBefore = 38,
                title = "El Sacrificio en f7",
                prompt = "Las negras parecen tener una sólida defensa. ¿Qué jugada legendaria entrega Euwe para quebrar la posición de Alekhine?",
                hint = "Sacrifica tu caballo capturando el peón de f7.",
                solutionSan = "Ne3",
                explanation = "¡20.Cxf7! Desmantela el enroque negro y permite el avance arrollador de los peones centrales."
            )
            )
        )
    }

    val game92: BookGame by lazy {
        BookGame(
            id = "alekhine_euwe_1937_m6",
            gameNumber = 92,
            title = "La Reconquista de la Corona Mundial",
            whitePlayer = "Alexander Alekhine",
            blackPlayer = "Max Euwe",
            event = "Holanda (Mundial Revancha 1937, 6ª)",
            year = "1937",
            result = "1-0",
            eco = "D37",
            openingName = "Defensa Eslava",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "En el match revancha de 1937 en Holanda, un Alekhine completamente rejuvenecido y sobrio asombró al mundo al recuperar la corona mundial con un juego brillante y demoledor.",
            strategicTheme = "Preparación teórica profunda y ataque implacable sobre el enroque.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 a6 Ba4 Nf6 O-O Nxe4 d4 b5 Bb3 d5 dxe5 Be6 c3 Be7 Bf4 O-O Nd4 Na5 f3 Nc5 Bc2 Nd7 Nd2 c5 Nf5 f6 exf6 Bxf5 Bxf5 Rxf6 g4 Nf8 Bg3 Rf7 Nb3 Nc4 Qe2 Bd6 Rae1 Bxg3 hxg3 Qd6 f4 a5 Qf2 Rc7 Rd1 a4 Be4 Ng6 Nc1 Rd8 Bf3 d4 cxd4 cxd4 Ne2 d3",
                annotations = mapOf(
            25 to Pair("!", "Alekhine abre la columna abierta f."),
            50 to Pair("!", "Victoria contundente que allana el camino al título.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "alekhine_euwe_1937_m6_c",
                plyBefore = 1,
                title = "La Ruptura Final",
                prompt = "¿Cómo derriba Alekhine la defensa de Euwe?",
                hint = "Avanza tu peón central hacia la casilla de ruptura.",
                solutionSan = "e5",
                explanation = "La ruptura central e5 desarticula por completo la defensa de Euwe."
            )
            )
        )
    }

    val game93: BookGame by lazy {
        BookGame(
            id = "alekhine_euwe_1937_m10",
            gameNumber = 93,
            title = "El Huracán Alekhine en La Haya",
            whitePlayer = "Alexander Alekhine",
            blackPlayer = "Max Euwe",
            event = "La Haya (Mundial Revancha 1937, 10ª)",
            year = "1937",
            result = "1-0",
            eco = "D64",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "Décima partida del match de 1937. Alekhine exhibe su mayor poderío combinativo barriendo las piezas negras con una secuencia de sacrificios que dejó a Euwe sin respuesta.",
            strategicTheme = "Fuerza combinativa suprema y red de mate en el flanco de rey.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 Nf6 c4 e6 Nc3 Bb4 Qc2 d5 cxd5 Qxd5 e3 c5 a3 Bxc3+ bxc3 Nc6 Nf3 O-O c4 Qd6 Bb2 cxd4 exd4 b6 Bd3 Bb7 O-O Rac8 Qe2 Qf4 Rad1 Rfe8 h3 Na5 Ne5 Be4 Rfe1 Bxd3 Qxd3 Red8 Bc1 Qh4 Qe2 Re8 g4 Nc6 Kg2 Nxe5 dxe5 Nh5 gxh5 Rxc4 Qf3 Rf8 h6 f5 Qg3 Qxg3+ fxg3 Rfc8 hxg7 Rc2+ Kf3 R2c3+ Be3 Rxa3 Rd7 Rc4 Kf2 Rc2+ Re2 Raa2 Rxc2 Rxc2+ Kf3 a5 Bh6 Rc8 Ra7",
                annotations = mapOf(
            30 to Pair("!", "Alekhine lanza sus piezas al asalto final."),
            55 to Pair("!", "Euwe abandona ante la inminencia del mate.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "alekhine_euwe_1937_m10_c",
                plyBefore = 66,
                title = "El Mate en Séptima",
                prompt = "¿Cómo corona Alekhine su asalto sobre Euwe?",
                hint = "Lleva tu torre a la séptima fila con jaque.",
                solutionSan = "Rd7",
                explanation = "El jaque en séptima desata la red de mate imparable."
            )
            )
        )
    }

    val game94: BookGame by lazy {
        BookGame(
            id = "alekhine_keres_1937",
            gameNumber = 94,
            title = "Duelo Intergeneracional en Margate",
            whitePlayer = "Alexander Alekhine",
            blackPlayer = "Paul Keres",
            event = "Margate",
            year = "1937",
            result = "1-0",
            eco = "D37",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "El choque en el torneo de Margate 1937 entre el legendario campeón del mundo y la joven estrella estonia Paul Keres, señalado como el principal aspirante a su trono.",
            strategicTheme = "Juego posicional magistral frente a la agresividad juvenil.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 a6 Ba4 Nf6 O-O Be7 d4 exd4 e5 Ne4 Re1 Nc5 Bxc6 dxc6 Nxd4 O-O Nc3 Re8 Be3 Bf8 f4 f6 exf6 Qxf6 Qf3 Bf5 Bf2 Rad8 Re3 Bg6 Rae1 Bd6 Rxe8+ Rxe8 Rxe8+ Bxe8 g3 Bf7 b4 Ne6 Ne4 Qg6 f5 Nxd4 Bxd4 Qh5 Qxh5 Bxh5 c3 Bf3 Nxd6 cxd6 f6 Bd5 a3",
                annotations = mapOf(
            25 to Pair("!", "Alekhine frena las tentativas de ataque de Keres."),
            50 to Pair("!", "Victoria técnica impecable de Alekhine.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "alekhine_keres_1937_c",
                plyBefore = 38,
                title = "La Invasión Central",
                prompt = "¿Cómo fuerza Alekhine la ventaja decisiva en el final?",
                hint = "Instala tu caballo en la casilla fuerte central.",
                solutionSan = "Rxe8+",
                explanation = "El caballo domina el centro y sella la victoria de Alekhine."
            )
            )
        )
    }

    val game95: BookGame by lazy {
        BookGame(
            id = "alekhine_capablanca_1938",
            gameNumber = 95,
            title = "El Último Duelo de Dos Titanes en AVRO",
            whitePlayer = "Alexander Alekhine",
            blackPlayer = "José Raúl Capablanca",
            event = "Holanda (Torneo AVRO)",
            year = "1938",
            result = "1-0",
            eco = "D30",
            openingName = "Defensa Francesa",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "La última partida oficial que disputaron en su vida los dos colosos del ajedrez mundial en el célebre torneo AVRO 1938. Alekhine se impuso tras una lucha posicional encarnizada que cerró el ciclo de su histórica rivalidad.",
            strategicTheme = "El último capítulo de la rivalidad más célebre de la historia del ajedrez.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e6 d4 d5 Nd2 Nf6 e5 Nfd7 Bd3 c5 c3 Nc6 Ne2 Qb6 Nf3 cxd4 cxd4 Bb4+ Kf1 Be7 a3 Nf8 b4 Bd7 Be3 Nd8 Nc3 a5 Na4 Qa7 b5 b6 g3 f5 Kg2 Nf7 Qd2 h6 h4 Nh7 h5 Nfg5 Nh4 Ne4 Qb2 Kf7 f3 Neg5 g4 fxg4 Bg6+ Kg8 f4 Nf3 Bxh7+ Rxh7 Ng6 Bd8 Rac1 Be8 Kg3 Qf7 Kxg4 Nh4 Nxh4 Qxh5+ Kg3 Qf7 Nf3",
                annotations = mapOf(
            30 to Pair("!", "Alekhine incrementa la presión en el flanco de dama."),
            60 to Pair("!", "Capablanca pierde por tiempo en posición perdida cerrando su rivalidad histórica.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "alekhine_capablanca_1938_c",
                plyBefore = 38,
                title = "La Invasión Decisiva",
                prompt = "¿Cómo fuerza Alekhine la capitulación de Capablanca?",
                hint = "Infiltra tu torre en la columna abierta.",
                solutionSan = "h4",
                explanation = "La torre en séptima fila resulta imparable y decide la partida."
            )
            )
        )
    }

    val game96: BookGame by lazy {
        BookGame(
            id = "alekhine_junge_1942",
            gameNumber = 96,
            title = "El Canto del Cisne en Varsovia",
            whitePlayer = "Alexander Alekhine",
            blackPlayer = "Klaus Junge",
            event = "Varsovia",
            year = "1942",
            result = "1-0",
            eco = "D37",
            openingName = "Defensa Semi-Eslava",
            chapter = GameChapter.ALEKHINE,
            historicalIntroduction = "Disputada en tiempos difíciles durante la Segunda Guerra Mundial frente a la gran promesa alemana Klaus Junge. A pesar de los años y el desgaste de la guerra, el genio combinativo de Alekhine brilló con intensidad inmarcesible.",
            strategicTheme = "Ataque fulgurante con sacrificios en el flanco de rey en plena madurez.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 c4 e6 Nf3 Nf6 g3 dxc4 Qa4+ Nbd7 Bg2 a6 Qxc4 b5 Qc6 Rb8 O-O Bb7 Qc2 c5 a4 Bxf3 Bxf3 cxd4 axb5 axb5 Rd1 Qb6 Nd2 e5 Nb3 Nc5 Nxc5 Bxc5 Ra6 Qxa6 Qxc5 Qe6 Bc6+ Nd7 Bxd7+ Kxd7 Qa7+ Kc6 Bd2 Rhc8 e4 Qb3 Ra1 b4 Ra6+ Kb5 Ra5+ Kc6 Qc5+ Kd7 Ra7+",
                annotations = mapOf(
            25 to Pair("!", "Alekhine lanza un asalto demoledor al enroque de Junge."),
            50 to Pair("!", "Victoria artística que demuestra el genio eterno de Alekhine.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "alekhine_junge_1942_c",
                plyBefore = 38,
                title = "El Remate al Enroque",
                prompt = "¿Cómo destruye Alekhine el refugio del rey negro?",
                hint = "Sacrifica tu alfil en la casilla f7.",
                solutionSan = "Bc6+",
                explanation = "El sacrificio de alfil abre el rey negro al mate forzado."
            )
            )
        )
    }

    val games: List<BookGame> by lazy {
        listOf(game84, game85, game86, game87, game88, game89, game90, game91, game92, game93, game94, game95, game96)
    }
}
