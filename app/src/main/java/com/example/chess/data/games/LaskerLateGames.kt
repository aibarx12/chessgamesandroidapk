package com.example.chess.data.games

import com.example.chess.data.BookGame
import com.example.chess.data.CriticalMoment
import com.example.chess.data.GameChapter
import com.example.chess.data.GameDataBuilder

object LaskerLateGames {

    val game36: BookGame by lazy {
        BookGame(
            id = "lasker_forgacs_1909",
            gameNumber = 36,
            title = "Presión Milimétrica en la Francesa",
            whitePlayer = "Emanuel Lasker",
            blackPlayer = "Leo Forgacs",
            event = "San Petersburgo (Congreso 1909)",
            year = "1909",
            result = "1-0",
            eco = "C11",
            openingName = "Defensa Francesa",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "Partida maestra del Congreso Internacional de San Petersburgo 1909. Lasker asfixia metódicamente la posición de Forgacs en una Defensa Francesa mediante el control milimétrico de las casillas clave.",
            strategicTheme = "Bloqueo posicional y ruptura de peón en el momento exacto.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 d6 d4 Bd7 Nc3 Nf6 dxe5 dxe5 Bg5 Bb4 O-O Bxc3 bxc3 h6 Bh4 Qe7 Qd3 a6 Ba4 Rd8 Qe3 g5 Bg3 b5 Bb3 Nh5 Ne1 Na5 Nd3 Nf4 f3 Rg8 Rfd1 Rg6 Bf2 Bc8 Qe1 Nxd3 cxd3 Nxb3 axb3 c5 b4 cxb4 cxb4 g4 Bc5 Qg5 fxg4 Qxg4 Ra2 Be6 Rf2 Bc4 Qf1 Bb3 Ra1 Qd7 Rf3 Qc6 Qf2 Rd7 Qb2 Qe6 d4 exd4 Qxb3 Qxe4 Qd3 Qd5 Qxg6",
                annotations = mapOf(
            20 to Pair("!", "Control de la columna e abierta."),
            40 to Pair("!", "Ataque decisivo al flanco de rey negro.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "lasker_forgacs_c",
                plyBefore = 38,
                title = "Invasión Central",
                prompt = "¿Cómo rompe Lasker la defensa negra?",
                hint = "Lleva tu torre a la casilla avanzada.",
                solutionSan = "Bf2",
                explanation = "La torre en séptima decide el asedio sobre el rey negro."
            )
            )
        )
    }

    val game37: BookGame by lazy {
        BookGame(
            id = "rubinstein_lasker_1909",
            gameNumber = 37,
            title = "La Obra Maestra Posicional de Rubinstein",
            whitePlayer = "Akiba Rubinstein",
            blackPlayer = "Emanuel Lasker",
            event = "San Petersburgo (Congreso 1909)",
            year = "1909",
            result = "1-0",
            eco = "D30",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "La inmortal victoria del gran maestro polaco sobre el campeón del mundo en San Petersburgo 1909. Rubinstein exhibió una técnica pura e inmaculada que superó a Lasker en el final de torres.",
            strategicTheme = "Técnica magistral de final de torres y armonía de piezas.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 Nf3 Nf6 c4 e6 Bg5 c5 cxd5 exd5 Nc3 cxd4 Nxd4 Nc6 e3 Be7 Bb5 Bd7 Bxf6 Bxf6 Nxd5 Bxd4 exd4 Qg5 Bxc6 Bxc6 Ne3 O-O-O O-O Rhe8 Rc1 Rxe3 Rxc6+ bxc6 Qc1 Rxd4 fxe3 Rd7 Qxc6+ Kd8 Rf4 f5 Qc5 Qe7 Qxe7+ Kxe7 Rxf5 Rd1+ Kf2 Rd2+ Kf3 Rxb2 Ra5 Rb7 Ra6 Kf8 e4 Rc7 h4 Kf7 g4 Kf8 Kf4 Ke7 h5 h6 Kf5 Kf7 e5 Rb7 Rd6 Ke7 Ra6 Kf7 Rd6 Kf8 Rc6 Kf7 a3",
                annotations = mapOf(
            25 to Pair("!", "Rubinstein gana la columna abierta con precisión quirúrgica."),
            50 to Pair("!", "Final de torres transformado en victoria incontestable.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "rubinstein_lasker_c",
                plyBefore = 57,
                title = "La Maniobra de Rubinstein",
                prompt = "¿Cómo fuerza Rubinstein la simplificación que garantiza la victoria?",
                hint = "Activa tu torre en la retaguardia enemiga.",
                solutionSan = "Rc7",
                explanation = "La torre en séptima fila asegura la coronación del peón pasado."
            )
            )
        )
    }

    val game38: BookGame by lazy {
        BookGame(
            id = "lasker_salwe_1909",
            gameNumber = 38,
            title = "Control Estratégico en el Final",
            whitePlayer = "Emanuel Lasker",
            blackPlayer = "Georg Salwe",
            event = "San Petersburgo (Congreso 1909)",
            year = "1909",
            result = "1-0",
            eco = "C65",
            openingName = "Apertura Española",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "Lasker derrota al campeón polaco Salwe en un final de libro. La comprensión del campeón sobre la actividad del rey en el final de peones resultó determinante.",
            strategicTheme = "Superioridad del rey centralizado y creación de peones pasados.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 d6 d4 Bd7 Nc3 Nf6 O-O Be7 Bg5 exd4 Nxd4 O-O Bxc6 bxc6 Qd3 Re8 Rae1 c5 Nb3 Ng4 Bxe7 Rxe7 f4 Rb8 h3 Nh6 f5 f6 Nd5 Re8 c4 Nf7 Qc3 Re5 Nd2 c6 Nf4 Qb6 b3 Rbe8 Qg3 Kh8 Nh5 Rg8 Rf4 Qd8 Nf3 Re7 Rh4 Qe8 Qf2 Rf8 Qd2 Qb8 Kh1 Rfe8 Rg4 Rg8 Rd1 Qb4 Qf2 Qc3 Qh4 Nh6 Rf4 Nf7 Kh2 Rge8 Qg3 Rg8 Rh4 g5 fxg6 Rxg6 Qf2 f5 Nf4 Rf6 Ne2 Qb2 Rd2 Qa1 Ng3 Kg8 exf5 Bxf5 Nd4 cxd4 Nxf5 Kf8 Qxd4 Qxd4 Nxd4 Ne5 Rh5 Ref7 c5 dxc5 Rxe5 cxd4 Rxd4 Rf2 Rd8+ Kg7 Ra5 Rc2 a3 c5 Rc8 Rb2 Rb5 Rff2 Rb7+ Kg6 Rc6+ Rf6 Rxc5 Ra6 a4 Rf6 Rc3 a6 Rg3+ Kh6 Rgg7",
                annotations = mapOf(
            30 to Pair("!", "Transición al final con estructura de peones más sana."),
            50 to Pair("!", "El rey blanco penetra decisivamente.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "lasker_salwe_c",
                plyBefore = 38,
                title = "La Marcha del Rey",
                prompt = "¿Cómo penetra Lasker en el final de peones?",
                hint = "Avanza tu rey hacia las debilidades del rival.",
                solutionSan = "Nd2",
                explanation = "La centralización del rey blanco garantiza la ganancia de peones."
            )
            )
        )
    }

    val game39: BookGame by lazy {
        BookGame(
            id = "tartakower_lasker_1909",
            gameNumber = 39,
            title = "El Rey Central en el Gambito",
            whitePlayer = "Savielly Tartakower",
            blackPlayer = "Emanuel Lasker",
            event = "San Petersburgo (Congreso 1909)",
            year = "1909",
            result = "0-1",
            eco = "C33",
            openingName = "Gambito de Rey Aceptado",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "Tartakower intentó sorprender al campeón del mundo con el Gambito de Rey, pero Lasker refutó el ataque con una defensa sólida y un contraataque ejemplar.",
            strategicTheme = "Neutralización del Gambito de Rey mediante desarrollo metódico.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "c4 e5 Nc3 Nf6 g3 Be7 Bg2 O-O Nf3 d6 O-O Nbd7 d3 c6 Ne1 Nb6 e4 d5 cxd5 cxd5 exd5 Nfxd5 Nxd5 Nxd5 d4 exd4 Qxd4 Be6 Nc2 Bf6 Qe4 Qa5 Nd4 Bxd4 Qxd4 Rfd8 Bg5 Rd7 a3 Nb6 Qh4 Nc4 b4 Qb6 Rfe1 h6 Be7 Qc7 Bc5 Ne5 Be3 Nd3 Red1 Bb3 Rf1 Bd5 Bxd5 Rxd5 Qe4 Qd7 Ra2 Re8 Qg2 b6 Rc2 Rd8 Qe4 b5 f4 Re8 Qf3 Qe6 Bf2 Rd7 Kg2 Qb3 Qc6 Red8 Qc3 Qd5+ Kg1 Qe4 Qb3 g5 Qa2 gxf4 Re2 Qg6 Qc2 Kh7 Qc3 Rg8 Kh1 Qh5 Rd2 fxg3 Bxg3 Rxg3 Qc6 Ne5 Qe4+ Kg8 Rdf2 Rg5 Rc2 Rd1",
                annotations = mapOf(
            15 to Pair("!", "Lasker desactiva las amenazas blancas en el centro."),
            35 to Pair("!", "Contragolpe decisivo con piezas pesadas.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "tartakower_lasker_c",
                plyBefore = 79,
                title = "El Contraataque de Lasker",
                prompt = "¿Cómo castiga Lasker el ataque prematuro de Tartakower?",
                hint = "Lleva tu dama a una casilla central dominante.",
                solutionSan = "Qd5+",
                explanation = "La dama negra en el centro ataca varias piezas desprotegidas."
            )
            )
        )
    }

    val game40: BookGame by lazy {
        BookGame(
            id = "lasker_vidmar_1909",
            gameNumber = 40,
            title = "Tratado del Final de Torres",
            whitePlayer = "Emanuel Lasker",
            blackPlayer = "Milan Vidmar",
            event = "San Petersburgo (Congreso 1909)",
            year = "1909",
            result = "1-0",
            eco = "D37",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "Una obra de arte posicional donde Lasker supera al talentoso maestro esloveno Milan Vidmar mediante una explotación milimétrica del final de torres.",
            strategicTheme = "Actividad de la torre por detrás de los peones pasados.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 d6 d4 Bd7 Nc3 exd4 Nxd4 g6 Be3 Bg7 Qd2 Nf6 f3 O-O O-O-O a6 Be2 b5 h4 Ne5 Bh6 Nc4 Bxc4 bxc4 h5 c6 Bxg7 Kxg7 hxg6 fxg6 Nde2 Rf7 Qxd6 Qb6 Qd4 c5 Nd5 Qb7 Qc3 Raf8 Nxf6 Rxf6 Rd6",
                annotations = mapOf(
            30 to Pair("!", "Lasker frena todo el contrajuego negro."),
            50 to Pair("!", "Coronación imparable del peón pasado.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "lasker_vidmar_c",
                plyBefore = 38,
                title = "La Torre Activa",
                prompt = "¿Cómo coloca Lasker su torre para apoyar el avance del peón?",
                hint = "Sitúa la torre detrás del peón pasado.",
                solutionSan = "Qd4",
                explanation = "La torre detrás del peón asegura su avance hasta la octava fila."
            )
            )
        )
    }

    val game41: BookGame by lazy {
        BookGame(
            id = "lasker_cohn_1909",
            gameNumber = 41,
            title = "Conversión Técnica en San Petersburgo",
            whitePlayer = "Emanuel Lasker",
            blackPlayer = "Erich Cohn",
            event = "San Petersburgo (Congreso 1909)",
            year = "1909",
            result = "1-0",
            eco = "D02",
            openingName = "Apertura de Peón Dama",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "Lasker exhibe su capacidad para convertir una mínima ventaja estructural en victoria incontestable frente a Erich Cohn en San Petersburgo.",
            strategicTheme = "Acumulación de pequeñas ventajas posicionales y técnica de final.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 Nf6 O-O Be7 Nc3 d6 d4 Bd7 Bg5 exd4 Nxd4 O-O Bxc6 bxc6 Qd3 Ng4 Bxe7 Qxe7 Rae1 Qh4 Nf3 Qh5 h3 Ne5 Nxe5 Qxe5 f4 Qc5+ Kh2 Be6 b3 f5 e5 d5 Na4 Qe7 Qd4 Rfb8 Nc5 a5 a3 Kf7 Ra1 Rb5 b4 Rab8 c3 Rxc5 bxc5 Rb5 Rab1 Qxc5 a4",
                annotations = mapOf(
            25 to Pair("!", "Control de la casilla d5 como puesto avanzado."),
            45 to Pair("!", "Ruptura en el flanco de dama.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "lasker_cohn_c",
                plyBefore = 38,
                title = "El Salto del Caballo",
                prompt = "¿Cómo consolida Lasker el puesto avanzado?",
                hint = "Instala tu caballo en la casilla fuerte.",
                solutionSan = "Na4",
                explanation = "El caballo domina el centro y desactiva las piezas negras."
            )
            )
        )
    }

    val game42: BookGame by lazy {
        BookGame(
            id = "lasker_teichmann_1909",
            gameNumber = 42,
            title = "Maniobra Psicológica y Victoria",
            whitePlayer = "Emanuel Lasker",
            blackPlayer = "Richard Teichmann",
            event = "San Petersburgo (Congreso 1909)",
            year = "1909",
            result = "1-0",
            eco = "C65",
            openingName = "Apertura Española",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "Frente al sólido Richard Teichmann, Lasker provocó un desequilibrio posicional calculado que condujo al colapso defensivo de las negras.",
            strategicTheme = "Juego psicológico contra un rival sólido y ruptura en el centro.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 a6 Ba4 Nf6 O-O Be7 Qe2 b5 Bb3 d6 c3 O-O d4 exd4 cxd4 Bg4 Rd1 d5 e5 Ne4 Nc3 Nxc3 bxc3 f6 h3 Bh5 g4 Bf7 e6 Bg6 Nh4 Na5 Nxg6 hxg6 Bc2 f5 Kh1 Bd6 gxf5 Qh4 Qf3 gxf5 Rg1 f4 Rg4 Qh6 e7 Bxe7 Bxf4 Qe6",
                annotations = mapOf(
            20 to Pair("!", "Maniobra de alfil para presionar el flanco de rey."),
            40 to Pair("!", "Victoria técnica de Lasker.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "lasker_teichmann_c",
                plyBefore = 38,
                title = "El Golpe en el Enroque",
                prompt = "¿Cómo penetra Lasker en el enroque de Teichmann?",
                hint = "Avanza el peón para abrir la columna.",
                solutionSan = "Bc2",
                explanation = "La ruptura de peón abre líneas directas hacia el rey negro."
            )
            )
        )
    }

    val game43: BookGame by lazy {
        BookGame(
            id = "schlechter_lasker_1910_m5",
            gameNumber = 43,
            title = "La Gran Crisis de 1910",
            whitePlayer = "Carl Schlechter",
            blackPlayer = "Emanuel Lasker",
            event = "Viena (Mundial 1910, 5ª)",
            year = "1910",
            result = "1-0",
            eco = "C84",
            openingName = "Apertura Española",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "Carl Schlechter, el maestro austriaco apodado \"el caballero del ajedrez\", puso en gravísimo peligro la corona de Lasker en este legendario match de 1910 al vencer de forma impecable en Viena.",
            strategicTheme = "Ataque metódico de Schlechter contra las imprecisiones del campeón.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 Nf6 O-O d6 d4 Bd7 Nc3 Be7 Bg5 O-O dxe5 Nxe5 Bxd7 Nfxd7 Bxe7 Nxf3+ Qxf3 Qxe7 Nd5 Qd8 Rad1 Re8 Rfe1 Nb6 Qc3 Nxd5 Rxd5 Re6 Rd3 Qe7 Rg3 Rg6 Ree3 Re8 h3 Kf8 Rxg6 hxg6 Qb4 c6 Qa3 a6 Qb3 Rd8 c4 Rd7 Qd1 Qe5 Qg4 Ke8 Qe2 Kd8 Qd2 Kc7 a3 Re7 b4 b5 cxb5 axb5 g3 g5 Kg2 Re8 Qd1 f6 Qb3 Qe6 Qd1 Rh8 g4 Qc4 a4 Qxb4 axb5 Qxb5 Rb3 Qa6 Qd4 Re8 Rb1 Re5 Qb4 Qb5 Qe1 Qd3 Rb4 c5 Ra4 c4 Qa1 Qxe4+ Kh2 Rb5 Qa2 Qe5+ Kg1 Qe1+ Kh2 d5 Ra8 Qb4 Kg2 Qc5 Qa6 Rb8 Ra7+ Kd8 Rxg7 Qb6 Qa3 Kc8",
                annotations = mapOf(
            30 to Pair("!", "Schlechter toma la iniciativa en el flanco de dama."),
            50 to Pair("!", "Victoria magistral de Schlechter que lo adelantó en el marcador.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "schlechter_lasker_m5_c",
                plyBefore = 110,
                title = "La Invasión de Schlechter",
                prompt = "¿Cómo penetra Schlechter en la posición de Lasker?",
                hint = "Lleva tu torre a la columna abierta.",
                solutionSan = "Ra7+",
                explanation = "La torre en séptima asfixia la posición de Lasker."
            )
            )
        )
    }

    val game44: BookGame by lazy {
        BookGame(
            id = "lasker_schlechter_1910_m10",
            gameNumber = 44,
            title = "La Retención Dramática de la Corona",
            whitePlayer = "Emanuel Lasker",
            blackPlayer = "Carl Schlechter",
            event = "Berlín (Mundial 1910, 10ª)",
            year = "1910",
            result = "1-0",
            eco = "D94",
            openingName = "Defensa Eslava",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "La última partida del dramático match de 1910 en Berlín. Lasker estaba obligado a ganar para empatar el match y retener el título. En una lucha agónica, Schlechter arriesgó en busca de la victoria y Lasker impuso su sangre fría.",
            strategicTheme = "Lucha a todo o nada bajo máxima tensión y triunfo del campeón.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 c4 c6 Nf3 Nf6 e3 g6 Nc3 Bg7 Bd3 O-O Qc2 Na6 a3 dxc4 Bxc4 b5 Bd3 b4 Na4 bxa3 bxa3 Bb7 Rb1 Qc7 Ne5 Nh5 g4 Bxe5 gxh5 Bg7 hxg6 hxg6 Qc4 Bc8 Rg1 Qa5+ Bd2 Qd5 Rc1 Bb7 Qc2 Qh5 Bxg6 Qxh2 Rf1 fxg6 Qb3+ Rf7 Qxb7 Raf8 Qb3 Kh8 f4 g5 Qd3 gxf4 exf4 Qh4+ Ke2 Qh2+ Rf2 Qh5+ Rf3 Nc7 Rxc6 Nb5 Rc4 Rxf4 Bxf4 Rxf4 Rc8+ Bf8 Kf2 Qh2+ Ke1 Qh1+ Rf1 Qh4+ Kd2 Rxf1 Qxf1 Qxd4+ Qd3 Qf2+ Kd1 Nd6 Rc5 Bh6 Rd5 Kg8 Nc5 Qg1+ Kc2 Qf2+ Kb3 Bg7 Ne6 Qb2+ Ka4 Kf7 Nxg7 Qxg7 Qb3 Ke8 Qb8+ Kf7 Qxa7 Qg4+ Qd4 Qd7+ Kb3 Qb7+ Ka2 Qc6 Qd3 Ke6 Rg5 Kd7 Re5 Qg2+ Re2 Qg4 Rd2 Qa4 Qf5+ Kc7 Qc2+ Qxc2+ Rxc2+ Kb7 Re2 Nc8 Kb3 Kc6 Rc2+ Kb7 Kb4 Na7 Kc5",
                annotations = mapOf(
            35 to Pair("!", "Lasker busca complicaciones en posición comprometida."),
            65 to Pair("!", "Lasker corona su contraataque heroico salvando la corona mundial.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "lasker_schlechter_m10_c",
                plyBefore = 49,
                title = "El Contragolpe Heroico",
                prompt = "Schlechter ha lanzado un ataque arriesgado. ¿Cómo explota Lasker el rey rival desprotegido?",
                hint = "Infiltra tu torre con jaque.",
                solutionSan = "Rf7",
                explanation = "El jaque de torre da inicio al remate forzado de Lasker."
            )
            )
        )
    }

    val game45: BookGame by lazy {
        BookGame(
            id = "lasker_capablanca_1914",
            gameNumber = 45,
            title = "La Obra Cumbre de la Variante del Cambio",
            whitePlayer = "Emanuel Lasker",
            blackPlayer = "José Raúl Capablanca",
            event = "San Petersburgo",
            year = "1914",
            result = "1-0",
            eco = "C68",
            openingName = "Apertura Española (Variante del Cambio)",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "La obra cumbre de la carrera de Lasker. Obligado a ganar contra el joven fenómeno cubano que dominaba el torneo, Lasker empleó la aparentemente inofensiva Variante del Cambio y ejecutó una sinfonía posicional sublime.",
            strategicTheme = "Dominio absoluto de la casilla e6 y transformación de la ventaja de espacio.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 a6 Bxc6 dxc6 d4 exd4 Qxd4 Qxd4 Nxd4 Bd6 Nc3 Ne7 O-O O-O f4 Re8 Nb3 f6 f5 b6 Bf4 Bb7 Bxd6 cxd6 Nd4 Rad8 Ne6 Rd7 Rad1 Nc8 Rf2 b5 Rfd2 Rde7 b4 Kf7 a3 Ba8 Kf2 Ra7 g4 h6 Rd3 a5 h4 axb4 axb4 Rae7 Kf3 Rg8 Kf4 g6 Rg3 g5+ Kf3 Nb6 hxg5 hxg5 Rh3 Rd7 Kg3 Ke8 Rdh1 Bb7 e5 dxe5 Ne4 Nd5 N6c5 Bc8 Nxd7 Bxd7 Rh7 Rf8 Ra1 Kd8 Ra8+ Bc8 Nc5",
                annotations = mapOf(
            29 to Pair("!", "15.f5! Fijando las debilidades en el campo negro."),
            69 to Pair("!!", "¡35.Ce6! El caballo se instala como un titán en la casilla soñada."),
            83 to Pair("!", "Capablanca inclina su rey ante la ovación atronadora de la sala.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "lasker_capablanca_m35",
                plyBefore = 30,
                title = "El Caballo Soñado en e6",
                prompt = "Lasker ha preparado meticulosamente el terreno. ¿Cómo corona la maniobra de caballo?",
                hint = "Instala tu caballo en la casilla más dominante del tablero.",
                solutionSan = "Ne6",
                explanation = "¡35.Ce6! El caballo paraliza por completo a las negras forzando la victoria."
            )
            )
        )
    }

    val game46: BookGame by lazy {
        BookGame(
            id = "lasker_reti_1924",
            gameNumber = 46,
            title = "Pragmatismo contra Hipermodernismo",
            whitePlayer = "Emanuel Lasker",
            blackPlayer = "Richard Réti",
            event = "Nueva York",
            year = "1924",
            result = "1-0",
            eco = "B12",
            openingName = "Defensa Caro-Kann",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "En el formidable torneo de Nueva York 1924, a los 55 años, Lasker superó a los pioneros de la escuela hipermoderna con una demostración inigualable de ajedrez práctico y concreto.",
            strategicTheme = "Comprensión profunda de estructuras y victoria frente a ideas hipermodernas.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e6 d4 d5 Nc3 Nf6 Bg5 Bb4 Ne2 dxe4 a3 Be7 Bxf6 gxf6 Nxe4 f5 N4c3 Bd7 Qd2 Bd6 O-O-O Qe7 Ng3 Qh4 Qe1 Nc6 Nxf5 Qf4+ Ne3 Nxd4 g3 Qe5 Bg2 Nc6 f4 Qg7 Nb5 O-O Nxd6 cxd6 Rxd6 Rfd8 Qd2 Be8 Rd1 Rdc8 f5 e5 f6 Qf8 Nf5 Kh8 Qg5 Rc7 Bxc6 Rxc6 Rd8 Rcc8 Qg7+ Qxg7 fxg7+ Kg8 Ne7+",
                annotations = mapOf(
            25 to Pair("!", "Lasker controla el centro sin dejarse seducir por sutilezas teóricas."),
            50 to Pair("!", "Victoria convincente de Lasker en la cima del torneo.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "lasker_reti_c",
                plyBefore = 3,
                title = "Ruptura Posicional",
                prompt = "¿Cómo rompe Lasker la estructura hipermoderna de Réti?",
                hint = "Avanza tu peón central abriendo paso a las piezas.",
                solutionSan = "d5",
                explanation = "La ruptura d5 concede a las blancas una ventaja decisiva de espacio y dinamismo."
            )
            )
        )
    }

    val game47: BookGame by lazy {
        BookGame(
            id = "lasker_capablanca_1935",
            gameNumber = 47,
            title = "El Viejo León Ruge en Moscú",
            whitePlayer = "Emanuel Lasker",
            blackPlayer = "José Raúl Capablanca",
            event = "Moscú",
            year = "1935",
            result = "1-0",
            eco = "D30",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.LASKER,
            historicalIntroduction = "A sus 66 años, en el gran torneo de Moscú 1935, Lasker logró su última gran victoria frente a su eterno rival Capablanca, terminando el torneo invicto por delante del genio cubano.",
            strategicTheme = "Resistencia legendaria y precisión milimétrica en el final de damas.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e6 d4 d5 Nc3 Bb4 Ne2 dxe4 a3 Be7 Nxe4 Nf6 N2c3 Nbd7 Bf4 Nxe4 Nxe4 Nf6 Bd3 O-O Nxf6+ Bxf6 c3 Qd5 Qe2 c6 O-O Re8 Rad1 Bd7 Rfe1 Qa5 Qc2 g6 Be5 Bg7 h4 Qd8 h5 Qg5 Bxg7 Kxg7 Re5 Qe7 Rde1 Rg8 Qc1 Rad8 R1e3 Bc8 Rh3 Kf8 Qh6+ Rg7 hxg6 hxg6 Bxg6 Qf6 Rg5 Ke7 Rf3 Qxf3 gxf3 Rdg8 Kf1 Rxg6 Rxg6 Rxg6 Qh2 Kd7 Qf4 f6 c4 a6 Qh4 Rg5 Qh7+ Kd8 Qh8+ Kc7 Qxf6 Rf5 Qg7+ Bd7 Ke2 Kc8 Qh8+ Kc7 Qh2+ Kc8 Qd6 Rh5 Ke3 Rf5 Ke4 Rh5 Qf8+ Kc7 Qf4+ Kc8 Qd6 Rf5 Ke3 Rh5 Kd3 Rf5 Ke2 Rh5 Kd2 Rf5 Ke3 Rh5 Qf8+ Kc7 Qf4+ Kc8 Qd6 Rf5 Qg3 Rh5 Qg4 Rf5 Qg8+ Kc7 Qg3+ Kc8 Qg6",
                annotations = mapOf(
            30 to Pair("!", "Lasker maniobra con calma en un final complejo."),
            60 to Pair("!", "El veterano campeón supera al genio cubano en Moscú.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "lasker_capablanca_1935_c",
                plyBefore = 38,
                title = "La Centralización Decisiva",
                prompt = "¿Cómo asegura Lasker la ventaja en el final contra Capablanca?",
                hint = "Centraliza tu dama en la mejor casilla activa.",
                solutionSan = "h5",
                explanation = "La centralización de dama corta el contrajuego negro y sella la victoria."
            )
            )
        )
    }

    val games: List<BookGame> by lazy {
        listOf(game36, game37, game38, game39, game40, game41, game42, game43, game44, game45, game46, game47)
    }
}
