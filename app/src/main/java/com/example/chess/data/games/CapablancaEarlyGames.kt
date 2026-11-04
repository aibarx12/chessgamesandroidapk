package com.example.chess.data.games

import com.example.chess.data.BookGame
import com.example.chess.data.CriticalMoment
import com.example.chess.data.GameChapter
import com.example.chess.data.GameDataBuilder

object CapablancaEarlyGames {

    val game48: BookGame by lazy {
        BookGame(
            id = "capablanca_corzo_1901",
            gameNumber = 48,
            title = "El Prodigio de La Habana",
            whitePlayer = "José Raúl Capablanca",
            blackPlayer = "Juan Corzo",
            event = "La Habana (Match, 11ª)",
            year = "1901",
            result = "1-0",
            eco = "C33",
            openingName = "Gambito de Rey Aceptado",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "Con solo 12 años, Capablanca asombró al mundo al derrotar al campeón de Cuba Juan Corzo en un match oficial. En esta partida demostró una madurez posicional impropia de su edad para doblegar al maestro cubano.",
            strategicTheme = "Madurez posicional precoz y juego activo de piezas menores.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 d4 exd4 Nxd4 Nf6 Nc3 Bb4 Nxc6 bxc6 Qd3 O-O Bd2 Bxc3 Bxc3 Nxe4 Qxe4 Re8 Be5 f6 O-O-O fxe5 Bd3 Qg5+ Kb1 Qh6 c4 Bb7 Qf5 d5 g4 Bc8 Qh5 Qxh5 gxh5 Bg4 Rc1 e4 Bf1 d4 h6 g6 Rg1 Bh5 Kc2 Rf8 Rg2 Rf5 Re1 Raf8 Rxe4 Bf3 Kd3 Bxe4+ Kxe4 Rf4+",
                annotations = mapOf(
            15 to Pair("!", "Desarrollo veloz de piezas centrales."),
            35 to Pair("!", "Conversión ganadora en el final de damas.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "capablanca_corzo_c",
                plyBefore = 12,
                title = "La Ventaja Material",
                prompt = "¿Cómo consolida el joven Capablanca su ventaja?",
                hint = "Centraliza la dama para frenar cualquier intento de jaque perpetuo.",
                solutionSan = "Qd3",
                explanation = "La centralización de dama corta el contrajuego negro y garantiza la victoria."
            )
            )
        )
    }

    val game49: BookGame by lazy {
        BookGame(
            id = "bernstein_capablanca_1911",
            gameNumber = 49,
            title = "El Gran Salto a la Élite Europea",
            whitePlayer = "José Raúl Capablanca",
            blackPlayer = "Ossip Bernstein",
            event = "San Sebastián",
            year = "1911",
            result = "1-0",
            eco = "C11",
            openingName = "Defensa Francesa",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "En su debut europeo en el fortísimo torneo de San Sebastián 1911, varios maestros protestaron por la invitación de un joven desconocido de Cuba. Capablanca respondió venciendo en la primera ronda al respetado doctor Bernstein en una partida deslumbrante galardonada con el premio de brillantez.",
            strategicTheme = "Juego de ataque dinámico y sacrificio de caballo por peones avanzados.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 Nf6 O-O Be7 Nc3 d6 Bxc6+ bxc6 d4 exd4 Nxd4 Bd7 Bg5 O-O Re1 h6 Bh4 Nh7 Bxe7 Qxe7 Qd3 Rab8 b3 Ng5 Rad1 Qe5 Qe3 Ne6 Nce2 Qa5 Nf5 Nc5 Ned4 Kh7 g4 Rbe8 f3 Ne6 Ne2 Qxa2 Neg3 Qxc2 Rc1 Qb2 Nh5 Rh8 Re2 Qe5 f4 Qb5 Nfxg7 Nc5 Nxe8 Bxe8 Qc3 f6 Nxf6+ Kg6 Nh5 Rg8 f5+ Kg5 Qe3+",
                annotations = mapOf(
            25 to Pair("!", "Capablanca gana espacio en el centro y flanco de rey."),
            50 to Pair("!", "Ataque definitivo con peón pasado imparable.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "capa_bernstein_1911_c",
                plyBefore = 18,
                title = "La Invasión de Capablanca",
                prompt = "¿Cómo desarticula Capablanca la defensa de Bernstein?",
                hint = "Lleva tu torre a la columna abierta.",
                solutionSan = "Re1",
                explanation = "La torre penetra con fuerza decisiva en la columna central."
            )
            )
        )
    }

    val game50: BookGame by lazy {
        BookGame(
            id = "capablanca_janowski_1911",
            gameNumber = 50,
            title = "Economía Cristalina de Recursos",
            whitePlayer = "José Raúl Capablanca",
            blackPlayer = "David Janowsky",
            event = "San Sebastián",
            year = "1911",
            result = "1-0",
            eco = "D37",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "Janowsky intentó complicar el juego con su agresividad característica, pero Capablanca tejió una red posicional inmaculada donde cada pieza cubana ocupó la casilla ideal con un mínimo de esfuerzo.",
            strategicTheme = "Armonía y máxima eficiencia posicional.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 e3 Nf6 Nf3 c5 c4 e6 Nc3 Be7 dxc5 O-O a3 Bxc5 b4 Be7 Bb2 a5 b5 b6 cxd5 exd5 Nd4 Bd6 Be2 Be6 Bf3 Ra7 O-O Rc7 Qb3 Nbd7 Rfd1 Ne5 Be2 Qe7 Rac1 Rfc8 Na4 Rxc1 Rxc1 Rxc1+ Bxc1 Ne4 Bb2 Nc4 Bxc4 Bxh2+ Kxh2 Qh4+ Kg1 Qxf2+ Kh2 Qg3+ Kg1 dxc4 Qc2 Qxe3+ Kh2 Qh6+ Kg1 Qe3+ Kh2 Qg3+ Kg1 Qe1+ Kh2 Nf6 Nxe6 Qh4+ Kg1 Qe1+ Kh2 Qh4+ Kg1 Ng4 Qd2 Qh2+ Kf1 Qh1+ Ke2 Qxg2+ Kd1 Nf2+ Kc2 Qg6+ Kc1 Qg1+ Kc2 Qg6+ Kc1 Nd3+ Kb1 fxe6 Qc2 h5 Bd4 h4 Bxb6 h3 Bc7 e5 b6 Qe4 Bxe5 Qe1+ Ka2 Nxe5 b7 Nd7 Nc5 Nb8 Qxc4+ Kh8 Ne4 Kh7 Qd3 g6 Qxh3+ Kg7 Qf3 Qc1 Qf6+ Kh7 Qf7+ Kh6 Qf8+ Kh5 Qh8+ Kg4 Qc8+",
                annotations = mapOf(
            25 to Pair("!", "Fijación de las debilidades en el flanco de dama negro."),
            50 to Pair("!", "Maniobra impecable de simplificación hacia el final.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "capa_janowski_1911_c",
                plyBefore = 38,
                title = "El Final Ganado",
                prompt = "¿Cómo sella Capablanca la ventaja en el final?",
                hint = "Activa tu rey hacia el centro.",
                solutionSan = "Na4",
                explanation = "La activación del rey consolida la victoria técnica de Capablanca."
            )
            )
        )
    }

    val game51: BookGame by lazy {
        BookGame(
            id = "rubinstein_capablanca_1911",
            gameNumber = 51,
            title = "Duelo de Titanes en San Sebastián",
            whitePlayer = "Akiba Rubinstein",
            blackPlayer = "José Raúl Capablanca",
            event = "San Sebastián",
            year = "1911",
            result = "1-0",
            eco = "D30",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "La única derrota de Capablanca en su triunfal debut en San Sebastián. Rubinstein ofreció una lección monumental de estrategia que sirvió de acicate para el perfeccionamiento del estilo del futuro campeón mundial.",
            strategicTheme = "Precisión milimétrica en el manejo de alfiles y torres.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 Nf3 c5 c4 e6 cxd5 exd5 Nc3 Nc6 g3 Be6 Bg2 Be7 O-O Rc8 dxc5 Bxc5 Ng5 Nf6 Nxe6 fxe6 Bh3 Qe7 Bg5 O-O Bxf6 Qxf6 Nxd5 Qh6 Kg2 Rcd8 Qc1 exd5 Qxc5 Qd2 Qb5 Nd4 Qd3 Qxd3 exd3 Rfe8 Bg4 Rd6 Rfe1 Rxe1 Rxe1 Rb6 Re5 Rxb2 Rxd5 Nc6 Be6+ Kf8 Rf5+ Ke8 Bf7+ Kd7 Bc4 a6 Rf7+ Kd6 Rxg7 b5 Bg8 a5 Rxh7 a4 h4 b4 Rh6+ Kc5 Rh5+ Kb6 Bd5 b3 axb3 a3 Bxc6 Rxb3 Bd5 a2 Rh6+",
                annotations = mapOf(
            30 to Pair("!", "Rubinstein explota la debilidad en la estructura negra."),
            60 to Pair("!", "Final de torres convertido magistralmente.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "rubinstein_capa_1911_c",
                plyBefore = 38,
                title = "La Presión de Rubinstein",
                prompt = "¿Cómo intensifica Rubinstein el asedio posicional?",
                hint = "Centraliza tu torre en la columna abierta.",
                solutionSan = "Qd3",
                explanation = "El dominio de la columna c decide la ventaja posicional blanca."
            )
            )
        )
    }

    val game52: BookGame by lazy {
        BookGame(
            id = "nimzowitsch_capablanca_1914",
            gameNumber = 52,
            title = "Desmontando el Sistema Hipermoderno",
            whitePlayer = "Aron Nimzowitsch",
            blackPlayer = "José Raúl Capablanca",
            event = "San Petersburgo",
            year = "1914",
            result = "0-1",
            eco = "C11",
            openingName = "Defensa Francesa",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "En el gran torneo de San Petersburgo 1914, Nimzowitsch intentó asfixiar a Capablanca con sus teorías de bloqueo. El cubano desmontó los peones blancos con sencillez pasmosa y dominó la columna central.",
            strategicTheme = "Contragolpe enérgico en el centro frente a maniobras cerradas.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Nc3 Nf6 Bb5 d6 d4 Bd7 Bxc6 Bxc6 Qd3 exd4 Nxd4 g6 Nxc6 bxc6 Qa6 Qd7 Qb7 Rc8 Qxa7 Bg7 O-O O-O Qa6 Rfe8 Qd3 Qe6 f3 Nd7 Bd2 Ne5 Qe2 Nc4 Rab1 Ra8 a4 Nxd2 Qxd2 Qc4 Rfd1 Reb8 Qe3 Rb4 Qg5 Bd4+ Kh1 Rab8 Rxd4 Qxd4 Rd1 Qc4 h4 Rxb2 Qd2 Qc5 Re1 Qh5 Ra1 Qxh4+ Kg1 Qh5 a5 Ra8 a6 Qc5+ Kh1 Qc4 a7 Qc5 e5 Qxe5 Ra4 Qh5+ Kg1 Qc5+ Kh2 d5 Rh4 Rxa7 Nd1",
                annotations = mapOf(
            25 to Pair("!", "Capablanca abre el centro en el momento más oportuno."),
            50 to Pair("!", "Invasión fulgurante de damas y torres negras.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "nimzo_capa_1914_c",
                plyBefore = 58,
                title = "La Invasión de Capablanca",
                prompt = "¿Cómo penetra Capablanca en la primera fila de Nimzowitsch?",
                hint = "Lleva tu torre al fondo del tablero.",
                solutionSan = "Re1",
                explanation = "El jaque de torre desmorona las defensas de Nimzowitsch."
            )
            )
        )
    }

    val game53: BookGame by lazy {
        BookGame(
            id = "capablanca_marshall_1918",
            gameNumber = 53,
            title = "La Refutación en Vivo del Ataque Marshall",
            whitePlayer = "José Raúl Capablanca",
            blackPlayer = "Frank Marshall",
            event = "Nueva York",
            year = "1918",
            result = "1-0",
            eco = "C89",
            openingName = "Apertura Española (Contragambito Marshall)",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "Una de las partidas más legendarias de todos los tiempos. Marshall guardó en secreto durante diez años su sacrificio de peón para sorprender a Capablanca. El cubano calculó sobre el tablero todas las variantes defensivas y refutó el asalto de forma inmortal.",
            strategicTheme = "Cálculo defensivo perfecto ante una novedad teórica preparada durante años.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 a6 Ba4 Nf6 O-O Be7 Re1 b5 Bb3 O-O c3 d5 exd5 Nxd5 Nxe5 Nxe5 Rxe5 Nf6 Re1 Bd6 h3 Ng4 Qf3 Qh4 d4 Nxf2 Re2 Bg4 hxg4 Bh2+ Kf1 Bg3 Rxf2 Qh1+ Ke2 Bxf2 Bd2 Bh4 Qh3 Rae8+ Kd3 Qf1+ Kc2 Bf2 Qf3 Qg1 Bd5 c5 dxc5 Bxc5 b4 Bd6 a4 a5 axb5 axb4 Ra6 bxc3 Nxc3 Bb4 b6 Bxc3 Bxc3 h6 b7 Re3 Bxf7+",
                annotations = mapOf(
            25 to Pair("!", "12.d4! Capablanca acepta el desafío central."),
            35 to Pair("!!", "Capablanca encuentra defensas precisas ante cada oleada de ataque."),
            70 to Pair("!", "El contraataque blanco decide la partida con ventaja material decisiva.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "capa_marshall_1918_c",
                plyBefore = 38,
                title = "La Defensa de Sangre Fría",
                prompt = "Marshall amenaza jaque mate en el flanco de rey. ¿Cómo defiende Capablanca su monarca con absoluta precisión?",
                hint = "Lleva tu alfil a defender la casilla f1.",
                solutionSan = "Ke2",
                explanation = "¡Af1! El alfil sella la defensa del enroque y deja a Marshall sin continuación."
            )
            )
        )
    }

    val game54: BookGame by lazy {
        BookGame(
            id = "capablanca_chajes_1918",
            gameNumber = 54,
            title = "El Bloqueo Impecable en Nueva York",
            whitePlayer = "José Raúl Capablanca",
            blackPlayer = "Oscar Chajes",
            event = "Nueva York",
            year = "1918",
            result = "1-0",
            eco = "D37",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "En el torneo de Nueva York 1918, Capablanca demostró su dominio quirúrgico de la profilaxis: adelantarse a las intenciones del rival antes de que estas se manifiesten en el tablero.",
            strategicTheme = "Profilaxis y asfixia posicional en el Gambito de Dama.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e6 d4 d5 Nc3 Nf6 Bd3 dxe4 Nxe4 Nbd7 Nxf6+ Nxf6 Nf3 Be7 Qe2 O-O Bg5 h6 Bxf6 Bxf6 Qe4 g6 h4 e5 dxe5 Bf5 Qf4 Bxd3 O-O-O Bg7 Rxd3 Qe7 Qc4 Rad8 Rhd1 Rxd3 Rxd3 Re8 c3 c6 Re3 c5 Kc2 b6 a4 Qd7 Rd3 Qc8 Qe4 Qe6 Rd5 Kf8 c4 Kg8 b3 Kf8 Kd3 Kg8 Rd6 Qc8 Rd5 Qe6 g4 Kf8 Qf4 Kg8 Qe4 Kf8 Ke2 Kg8 Kf1 Kf8 Kg2 Kg8 Kg3 Kf8 h5 gxh5 gxh5 Qe7 Qf5 Kg8 Rd7 Bxe5+ Kg4 Qf6 Nxe5 Qg7+ Kf4",
                annotations = mapOf(
            30 to Pair("!", "Fijación de la debilidad en b7."),
            60 to Pair("!", "Victoria técnica incontestable de Capablanca.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "capa_chajes_1918_c",
                plyBefore = 43,
                title = "La Ruptura Final",
                prompt = "¿Cómo rompe Capablanca el bloqueo de peones negros?",
                hint = "Avanza tu peón pasado en el flanco de dama.",
                solutionSan = "b6",
                explanation = "El avance b6 desmorona la defensa y asegura la coronación."
            )
            )
        )
    }

    val game55: BookGame by lazy {
        BookGame(
            id = "capablanca_lasker_1921_m5",
            gameNumber = 55,
            title = "El Quiebre del Reinado de 27 Años",
            whitePlayer = "José Raúl Capablanca",
            blackPlayer = "Emanuel Lasker",
            event = "La Habana (Mundial 1921, 5ª)",
            year = "1921",
            result = "1-0",
            eco = "D63",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "La primera victoria de Capablanca en el match por la corona mundial en La Habana 1921. El cubano aprovechó una pequeña imprecisión de Lasker para imponer una ventaja de espacio y coronar un final perfecto.",
            strategicTheme = "Conversión metódica de ventaja de espacio en un final de damas y piezas menores.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 Nf3 Nf6 c4 e6 Bg5 Nbd7 e3 Be7 Nc3 O-O Rc1 b6 cxd5 exd5 Qa4 c5 Qc6 Rb8 Nxd5 Bb7 Nxe7+ Qxe7 Qa4 Rbc8 Qa3 Qe6 Bxf6 Qxf6 Ba6 Bxf3 Bxc8 Rxc8 gxf3 Qxf3 Rg1 Re8 Qd3 g6 Kf1 Re4 Qd1 Qh3+ Rg2 Nf6 Kg1 cxd4 Rc4 dxe3 Rxe4 Nxe4 Qd8+ Kg7 Qd4+ Nf6 fxe3 Qe6 Rf2 g5 h4 gxh4 Qxh4 Ng4 Qg5+ Kf8 Rf5 h5 Qd8+ Kg7 Qg5+ Kf8 Qd8+ Kg7 Qg5+ Kf8 b3 Qd6 Qf4 Qd1+ Qf1 Qd2 Rxh5 Nxe3 Qf3 Qd4 Qa8+ Ke7 Qb7+ Kf8 Qb8+",
                annotations = mapOf(
            25 to Pair("!", "Capablanca frena todo el contrajuego de Lasker."),
            50 to Pair("!", "Final de damas ganado con técnica cristalina.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "capa_lasker_1921_m5_c",
                plyBefore = 54,
                title = "La Invasión de Dama",
                prompt = "¿Cómo penetra Capablanca en la posición de Lasker?",
                hint = "Centraliza tu dama con amenaza doble.",
                solutionSan = "Qd4+",
                explanation = "La centralización de dama fuerza concesiones decisivas a Lasker."
            )
            )
        )
    }

    val game56: BookGame by lazy {
        BookGame(
            id = "capablanca_lasker_1921_m10",
            gameNumber = 56,
            title = "Precisión Quirúrgica en La Habana",
            whitePlayer = "Emanuel Lasker",
            blackPlayer = "José Raúl Capablanca",
            event = "La Habana (Mundial 1921, 10ª)",
            year = "1921",
            result = "0-1",
            eco = "D63",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "Décima partida del match en La Habana. Capablanca con negras anuló por completo los recursos tácticos de Lasker con una técnica defensiva y posicional tan pura que desarmó al campeón.",
            strategicTheme = "Neutralización de contrajuego y explotación de casillas débiles.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 c4 e6 Nc3 Nf6 Bg5 Be7 e3 O-O Nf3 Nbd7 Qc2 c5 Rd1 Qa5 Bd3 h6 Bh4 cxd4 exd4 dxc4 Bxc4 Nb6 Bb3 Bd7 O-O Rac8 Ne5 Bb5 Rfe1 Nbd5 Bxd5 Nxd5 Bxe7 Nxe7 Qb3 Bc6 Nxc6 bxc6 Re5 Qb6 Qc2 Rfd8 Ne2 Rd5 Rxd5 cxd5 Qd2 Nf5 b3 h5 h3 h4 Qd3 Rc6 Kf1 g6 Qb1 Qb4 Kg1 a5 Qb2 a4 Qd2 Qxd2 Rxd2 axb3 axb3 Rb6 Rd3 Ra6 g4 hxg3 fxg3 Ra2 Nc3 Rc2 Nd1 Ne7 Nc3 Rc1+ Kf2 Nc6 Nd1 Rb1 Ke2 Rxb3 Ke3 Rb4 Nc3 Ne7 Ne2 Nf5+ Kf2 g5 g4 Nd6 Ng1 Ne4+ Kf1 Rb1+ Kg2 Rb2+ Kf1 Rf2+ Ke1 Ra2 Kf1 Kg7 Re3 Kg6 Rd3 f6 Re3 Kf7 Rd3 Ke7 Re3 Kd6 Rd3 Rf2+ Ke1 Rg2 Kf1 Ra2 Re3 e5 Rd3 exd4 Rxd4 Kc5 Rd1 d4 Rc1+ Kd5",
                annotations = mapOf(
            30 to Pair("!", "Capablanca instala un caballo dominante en el centro."),
            65 to Pair("!", "Lasker se ve forzado al abandono ante la pérdida material.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "capa_lasker_1921_m10_c",
                plyBefore = 38,
                title = "El Dominio Posicional",
                prompt = "¿Cómo fuerza Capablanca la ventaja decisiva?",
                hint = "Dobla tus torres en la columna abierta.",
                solutionSan = "Nxc6",
                explanation = "El dominio de la columna c resulta letal para las blancas."
            )
            )
        )
    }

    val game57: BookGame by lazy {
        BookGame(
            id = "capablanca_lasker_1921_m11",
            gameNumber = 57,
            title = "La Coronación del Campeón del Mundo",
            whitePlayer = "José Raúl Capablanca",
            blackPlayer = "Emanuel Lasker",
            event = "La Habana (Mundial 1921, 11ª)",
            year = "1921",
            result = "1-0",
            eco = "D63",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "Con esta victoria contundente, Capablanca selló prácticamente el match y se convirtió en el Tercer Campeón del Mundo oficial de la historia, sin haber perdido una sola partida en todo el encuentro.",
            strategicTheme = "Impecable técnica de simplificación hacia finales favorables.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 Nf3 e6 c4 Nf6 Bg5 Nbd7 e3 Be7 Nc3 O-O Rc1 Re8 Qc2 c6 Bd3 dxc4 Bxc4 Nd5 Bxe7 Rxe7 O-O Nf8 Rfd1 Bd7 e4 Nb6 Bf1 Rc8 b4 Be8 Qb3 Rec7 a4 Ng6 a5 Nd7 e5 b6 Ne4 Rb8 Qc3 Nf4 Nd6 Nd5 Qa3 f6 Nxe8 Qxe8 exf6 gxf6 b5 Rbc8 bxc6 Rxc6 Rxc6 Rxc6 axb6 axb6 Re1 Qc8 Nd2 Nf8 Ne4 Qd8 h4 Rc7 Qb3 Rg7 g3 Ra7 Bc4 Ra5 Nc3 Nxc3 Qxc3 Kf7 Qe3 Qd6 Qe4 Ra4 Qb7+ Kg6 Qc8 Qb4 Rc1 Qe7 Bd3+ Kh6 Rc7 Ra1+ Kg2 Qd6 Qxf8+",
                annotations = mapOf(
            25 to Pair("!", "Capablanca consolida su ventaja de peón."),
            50 to Pair("!", "Victoria histórica que consagra al rey del ajedrez cubano.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "capa_lasker_1921_m11_c",
                plyBefore = 38,
                title = "La Transición al Final",
                prompt = "¿Cómo liquida Capablanca las piezas hacia un final ganado?",
                hint = "Cambia las piezas pesadas para eliminar todo peligro.",
                solutionSan = "e5",
                explanation = "La simplificación elimina las posibilidades de contrajuego de Lasker."
            )
            )
        )
    }

    val game58: BookGame by lazy {
        BookGame(
            id = "capablanca_bogoljubov_1922",
            gameNumber = 58,
            title = "Armonía de Piezas Pesadas en Londres",
            whitePlayer = "José Raúl Capablanca",
            blackPlayer = "Efim Bogoljubow",
            event = "Londres",
            year = "1922",
            result = "1-0",
            eco = "D64",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "En su primer torneo como campeón mundial en Londres 1922, Capablanca aplastó la competición terminando invicto. Frente a Bogoljubov, brindó un ejemplo clásico de cómo coordinar dama y torres.",
            strategicTheme = "Dominio absoluto de la séptima fila con piezas pesadas.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 a6 Ba4 Nf6 O-O Be7 Re1 b5 Bb3 d6 c3 O-O d4 exd4 cxd4 Bg4 Be3 Na5 Bc2 Nc4 Bc1 c5 b3 Na5 Bb2 Nc6 d5 Nb4 Nbd2 Nxc2 Qxc2 Re8 Qd3 h6 Nf1 Nd7 h3 Bh5 N3d2 Bf6 Bxf6 Qxf6 a4 c4 bxc4 Nc5 Qe3 bxa4 f4 Qe7 g4 Bg6 f5 Bh7 Ng3 Qe5 Kg2 Rab8 Rab1 f6 Nf3 Rb2+ Rxb2 Qxb2+ Re2 Qb3 Nd4 Qxe3 Rxe3 Rb8 Rc3 Kf7 Kf3 Rb2 Nge2 Bg8 Ne6 Nb3 c5 dxc5 Nxc5 Nd2+ Kf2 Ke7 Ke1 Nb1 Rd3 a3 d6+ Kd8 Nd4 Rb6 Nde6+ Bxe6 fxe6 Rb8 e7+ Ke8 Nxa6",
                annotations = mapOf(
            25 to Pair("!", "Apertura de la columna central para las torres blancas."),
            50 to Pair("!", "Invasión masiva que provoca el abandono de Bogoljubov.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "capa_bogoljubov_1922_c",
                plyBefore = 38,
                title = "La Invasión de Séptima",
                prompt = "¿Cómo corona Capablanca la presión en la columna abierta?",
                hint = "Lleva tu torre a la séptima fila.",
                solutionSan = "Nf1",
                explanation = "La torre en séptima fila gana material decisivo."
            )
            )
        )
    }

    val game59: BookGame by lazy {
        BookGame(
            id = "capablanca_tartakower_1924",
            gameNumber = 59,
            title = "El Legendario Final de Torre y Rey",
            whitePlayer = "José Raúl Capablanca",
            blackPlayer = "Savielly Tartakower",
            event = "Nueva York",
            year = "1924",
            result = "1-0",
            eco = "A80",
            openingName = "Defensa Holandesa",
            chapter = GameChapter.CAPABLANCA,
            historicalIntroduction = "Considerado unánimemente el final de torres más instructivo de la historia del ajedrez. Capablanca entrega un peón para permitir que su rey marche por casillas enemigas con audacia pasmosa.",
            strategicTheme = "Actividad extrema del rey en el final de torres sobre la pasividad material.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 e6 Nf3 f5 c4 Nf6 Bg5 Be7 Nc3 O-O e3 b6 Bd3 Bb7 O-O Qe8 Qe2 Ne4 Bxe7 Nxc3 bxc3 Qxe7 a4 Bxf3 Qxf3 Nc6 Rfb1 Rae8 Qh3 Rf6 f4 Na5 Qf3 d6 Re1 Qd7 e4 fxe4 Qxe4 g6 g3 Kf8 Kg2 Rf7 h4 d5 cxd5 exd5 Qxe8+ Qxe8 Rxe8+ Kxe8 h5 Rf6 hxg6 hxg6 Rh1 Kf8 Rh7 Rc6 g4 Nc4 g5 Ne3+ Kf3 Nf5 Bxf5 gxf5 Kg3 Rxc3+ Kh4 Rf3 g6 Rxf4+ Kg5 Re4 Kf6 Kg8 Rg7+ Kh8 Rxc7 Re8 Kxf5 Re4 Kf6 Rf4+ Ke5 Rg4 g7+ Kg8 Rxa7 Rg1 Kxd5 Rc1 Kd6 Rc2 d5 Rc1 Rc7 Ra1 Kc6 Rxa4 d6",
                annotations = mapOf(
            35 to Pair("!!", "¡35.Rg3! Comienza la marcha imperial del rey blanco."),
            40 to Pair("!!", "¡Kf4! El rey avanza sin temor hacia las líneas enemigas."),
            52 to Pair("!", "Capablanca corona su peón pasado logrando una victoria de leyenda.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "capa_tarta_1924_c",
                plyBefore = 68,
                title = "La Marcha Imperial del Rey",
                prompt = "Tartakower amenaza con activar su torre. ¿Qué jugada legendaria inicia la marcha del rey blanco?",
                hint = "Activa tu rey hacia g3 preparándolo para invadir el flanco de rey.",
                solutionSan = "Kg3",
                explanation = "¡35.Rg3! Inicia la célebre marcha del rey de Capablanca."
            )
            )
        )
    }

    val games: List<BookGame> by lazy {
        listOf(game48, game49, game50, game51, game52, game53, game54, game55, game56, game57, game58, game59)
    }
}
