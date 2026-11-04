package com.example.chess.data.games

import com.example.chess.data.BookGame
import com.example.chess.data.CriticalMoment
import com.example.chess.data.GameChapter
import com.example.chess.data.GameDataBuilder

object PioneersRomanticGames {

    val game1: BookGame by lazy {
        BookGame(
            id = "mcdonnell_labourdonnais_1834",
            gameNumber = 1,
            title = "La Avalancha de Peones Centrales",
            whitePlayer = "Alexander McDonnell",
            blackPlayer = "Louis de La Bourdonnais",
            event = "Londres (4º match, 16ª)",
            year = "1834",
            result = "0-1",
            eco = "B32",
            openingName = "Defensa Siciliana (Variante Löwenthal)",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "Disputada en el Club Westminster de Londres en el célebre duelo anglo-francés de 1834. Representa la primera gran batalla estratégica del ajedrez moderno antes de la era oficial. La Bourdonnais demostró de forma genial la fuerza arrolladora de un trío de peones pasados móviles en el centro.",
            strategicTheme = "Estructura dinámica de peones centrales y sacrificio de calidad para desatar el avance de peones ligados.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 c5 Nf3 Nc6 d4 cxd4 Nxd4 e5 Nxc6 bxc6 Bc4 Nf6 Bg5 Be7 Qe2 d5 Bxf6 Bxf6 Bb3 O-O O-O a5 exd5 cxd5 Rd1 d4 c4 Qb6 Bc2 Bb7 Nd2 Rae8 Ne4 Bd8 c5 Qc6 f3 Be7 Rac1 f5 Qc4+ Kh8 Ba4 Qh6 Bxe8 fxe4 c6 exf3 Rc2 Qe3+ Kh1 Bc8 Bd7 f2 Rf1 d3 Rc3 Qe2 Qxd3 Qxd3 Rxd3 Ba6 Rdd1 Bxf1 Rxf1 Bd6 g3 e4 Kg2 e3 Bg4 g6 Be2 Rf5 Rd1 Rc5 Kf3 Rxc6 Kxe3 Bc5+ Kf3 Rf6+ Kg2 Re6 Kf1 Bb6 Rd5 Rc6 a4 Rc1+ Rd1 Rc2 b3 Rb2 Bc4 Bc5 h4 Kg7 g4 Kf6 g5+ Ke7 Bg8 Kf8 Bc4 Ke7 Bg8 Kf8 Bc4",
                annotations = mapOf(
            8 to Pair("!", "La Bourdonnais rompe enérgicamente en el centro con d5."),
            46 to Pair("!", "Sacrificio posicional de calidad para liberar los peones pasados."),
            50 to Pair("!!", "¡25...De3+! Penetración mortal que consagra el peón f.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "mcdonnell_m25",
                plyBefore = 49,
                title = "La Invasión Decisiva",
                prompt = "Las negras han sacrificado calidad para activar sus peones pasados. ¿Cómo penetra la dama negra en la posición blanca con máxima fuerza?",
                hint = "Busca un jaque directo de dama que obligue al rey blanco a refugiarse en la esquina.",
                solutionSan = "Qe3+",
                explanation = "¡25...De3+! Conduce al rey blanco a h1 e impide la consolidación blanca, dejando el peón f imparable."
            )
            )
        )
    }

    val game2: BookGame by lazy {
        BookGame(
            id = "saint_amant_staunton_1843",
            gameNumber = 2,
            title = "El Triunfo del Enfoque Posicional",
            whitePlayer = "Pierre Saint-Amant",
            blackPlayer = "Howard Staunton",
            event = "París (Match vital, 21ª)",
            year = "1843",
            result = "0-1",
            eco = "D30",
            openingName = "Gambito de Dama Declinado",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "El histórico duelo disputado en el Café de la Régence de París en 1843. Staunton aplicó por primera vez principios posicionales rigurosos: presión sobre casillas débiles, maniobras pacientes y dominio metódico de las columnas abiertas.",
            strategicTheme = "Juego estratégico cerrado, control de casillas clave y técnica de simplificación hacia un final superior.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "d4 d5 c4 e6 e3 c5 Nc3 Nf6 Nf3 Be7 Bd3 b6 O-O O-O b3 Bb7 cxd5 exd5 Qc2 Nc6 a3 a6 Rd1 cxd4 exd4 h6 b4 Bd6 Re1 b5 h3 Rc8 Qb3 Qc7 Bd2 Qb6 Be3 Ne7 Rac1 Nh5 Qd1 Nf6 Nh4 Rc7 Qd2 Nh7 Qc2 Nf6 Kh1 Ne8 Nf5 Nxf5 Bxf5 a5 Qb3 axb4 axb4 Rc4 Na2 Nf6 Bd3 Qc6 Qb2 Qd7 Kg1 Nh5 Qd2 f5 f4 Ng3 Bxc4 dxc4 Qb2 Rf6 Nc3 Ne4 Re2 Rg6 Rd1 Nxc3 Qxc3 Bf3 Rde1 Bxe2 Rxe2 Qe7 Qb2 Re6 Kf2 Re4 Qa2 Kf7 g3 Qb7 Qa3 Re8 Qc3 Qh1 h4 g5 Qe1 Qh2+ Kf1 Qh3+ Kg1 Qg4 hxg5 Bxf4 Bxf4 Qxe2 Qxe2 Rxe2 gxh6 c3 Kf1 Re4 Bc1 Kg6 d5 c2 Bd2 Rxb4 d6 Rd4 Ke2 Rxd6 Ke3 Kxh6 Ke2+ Kg6 Ke1 b4",
                annotations = mapOf(
            1 to Pair("!", "Apertura sólida buscando el control del centro."),
            30 to Pair("!", "Maniobra de alfil para afianzar el dominio de casillas negras.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "staunton_m30",
                plyBefore = 7,
                title = "Maniobra Estratégica",
                prompt = "¿Cómo consolida Staunton la ventaja estructural de peones en el flanco de dama?",
                hint = "Centraliza tu pieza menor para neutralizar la iniciativa rival.",
                solutionSan = "Nf6",
                explanation = "Staunton refuerza el centro y desactiva las posibles rupturas tácticas de Saint-Amant."
            )
            )
        )
    }

    val game3: BookGame by lazy {
        BookGame(
            id = "anderssen_kieseritzky_1851",
            gameNumber = 3,
            title = "La Inmortal: Sacrificio Total en Simpson's",
            whitePlayer = "Adolf Anderssen",
            blackPlayer = "Lionel Kieseritzky",
            event = "Londres (Simpson's-in-the-Strand)",
            year = "1851",
            result = "1-0",
            eco = "C33",
            openingName = "Gambito de Rey (Gambito de Alfil)",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "Disputada durante un descanso del Primer Torneo Internacional de Londres en 1851. La cima del romanticismo ajedrecístico: Anderssen entrega un alfil, ambas torres y por último la dama para asestar un mate inolvidable con sus tres piezas menores.",
            strategicTheme = "Sacrificio supremo de material por tiempo, diagonales abiertas y red de mate sobre el rey sin enrocar.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 f4 exf4 Bc4 Qh4+ Kf1 b5 Bxb5 Nf6 Nf3 Qh6 d3 Nh5 Nh4 Qg5 Nf5 c6 g4 Nf6 Rg1 cxb5 h4 Qg6 h5 Qg5 Qf3 Ng8 Bxf4 Qf6 Nc3 Bc5 Nd5 Qxb2 Bd6 Bxg1 e5 Qxa1+ Ke2 Na6 Nxg7+ Kd8 Qf6+ Nxf6 Be7#",
                annotations = mapOf(
            35 to Pair("!", "Sacrificio de alfil en b5 para abrir el centro."),
            37 to Pair("!!", "¡18.Ad6!! Anderssen entrega ambas torres en las esquinas."),
            43 to Pair("!!", "¡22.Df6+!! Sacrificio de dama definitivo para desviar al caballo defensor.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "immortal_m18",
                plyBefore = 34,
                title = "El Doble Sacrificio de Torre",
                prompt = "Las negras acaban de capturar en b2 amenazando la torre de a1. ¿Cómo teje Anderssen la red de mate?",
                hint = "Instala tu alfil en una casilla donde corte el escape del rey negro en e8.",
                solutionSan = "Bd6",
                explanation = "¡18.Ad6!! Ignora ambas torres para estrangular las casillas oscuras del rey rival."
            ),
            CriticalMoment(
                id = "immortal_m22",
                plyBefore = 42,
                title = "El Remate Inmortal",
                prompt = "El rey negro está cercado, pero el caballo de g8 vigila e7. ¿Cómo desvías a ese defensor?",
                hint = "Entrega tu dama para atraer al caballo negro.",
                solutionSan = "Qf6+",
                explanation = "¡22.Df6+!! Obliga a 22...Cxf6, dejando libre la casilla e7 para 23.Ae7#."
            )
            )
        )
    }

    val game4: BookGame by lazy {
        BookGame(
            id = "anderssen_dufresne_1852",
            gameNumber = 4,
            title = "La Siempreviva: Fuego Táctico en el Centro",
            whitePlayer = "Adolf Anderssen",
            blackPlayer = "Jean Dufresne",
            event = "Berlín",
            year = "1852",
            result = "1-0",
            eco = "C52",
            openingName = "Gambito Evans",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "Bautizada por Wilhelm Steinitz como una corona de laureles imperecedera para el maestro alemán. Dufresne intentó contraatacar en el flanco de rey, pero Anderssen desató una combinación fulgurante con sacrificios sucesivos en d7.",
            strategicTheme = "Ataque sobre el rey central desprotegido mediante sacrificios de atracción y jaques a la descubierta.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bc4 Bc5 b4 Bxb4 c3 Ba5 d4 exd4 O-O d3 Qb3 Qf6 e5 Qg6 Re1 Nge7 Ba3 b5 Qxb5 Rb8 Qa4 Bb6 Nbd2 Bb7 Ne4 Qf5 Bxd3 Qh5 Nf6+ gxf6 exf6 Rg8 Rad1 Qxf3 Rxe7+ Nxe7 Qxd7+ Kxd7 Bf5+ Ke8 Bd7+ Kf8 Bxe7#",
                annotations = mapOf(
            37 to Pair("!!", "¡19.Rad1! Dejando la dama en el aire para mantener la presión sobre d7."),
            41 to Pair("!!", "¡21.Dxd7+!! Sacrificio de dama que corona una de las combinaciones más bellas del ajedrez.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "evergreen_m21",
                plyBefore = 40,
                title = "El Sacrificio de Dama en d7",
                prompt = "Todas las piezas blancas convergen sobre el rey negro. ¿Cómo rematas la partida de forma forzada?",
                hint = "Entrega la dama en d7 para atraer al rey o caballo negro hacia la descubierta mortal.",
                solutionSan = "Qxd7+",
                explanation = "¡21.Dxd7+!! Tras 21...Cxd7 sigue 22.Af5+ con jaque doble a la descubierta y mate en dos."
            )
            )
        )
    }

    val game5: BookGame by lazy {
        BookGame(
            id = "rosanes_anderssen_1863",
            gameNumber = 5,
            title = "La Furia del Contragambito en Breslau",
            whitePlayer = "Jakob Rosanes",
            blackPlayer = "Adolf Anderssen",
            event = "Breslau",
            year = "1863",
            result = "0-1",
            eco = "C39",
            openingName = "Gambito de Rey Aceptado",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "Disputada en la ciudad natal de Anderssen contra su aventajado discípulo Jakob Rosanes. Anderssen demuestra cómo combatir el Gambito de Rey tomando la iniciativa de inmediato con piezas activas.",
            strategicTheme = "Contraataque feroz con negras sacrificando peones para abrir líneas contra el rey blanco.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 f4 exf4 Nf3 g5 h4 g4 Ne5 Nf6 Bc4 d5 exd5 Bd6 d4 Nh5 Bb5+ c6 dxc6 bxc6 Nxc6 Nxc6 Bxc6+ Kf8 Bxa8 Ng3 Rh2 Bf5 Bd5 Kg7 Nc3 Re8+ Kf2 Qb6 Na4 Qa6 Nc3 Be5 a4 Qf1+ Qxf1 Bxd4+ Be3 Rxe3 Kg1 Re1#",
                annotations = mapOf(
            20 to Pair("!", "Ruptura central con negras descolocando al rey blanco."),
            40 to Pair("!!", "Ataque fulminante sobre la primera fila.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "rosanes_m18",
                plyBefore = 31,
                title = "Ruptura Dinámica",
                prompt = "¿Cómo explota Anderssen la debilidad del rey blanco en el centro?",
                hint = "Abre la columna rey con una captura activa.",
                solutionSan = "Re8+",
                explanation = "La torre entra con jaque forzando la pérdida de la coordinación defensiva blanca."
            )
            )
        )
    }

    val game6: BookGame by lazy {
        BookGame(
            id = "paulsen_morphy_1857",
            gameNumber = 6,
            title = "El Sacrificio Clásico de Dama en Nueva York",
            whitePlayer = "Louis Paulsen",
            blackPlayer = "Paul Morphy",
            event = "Nueva York (1st American Chess Congress)",
            year = "1857",
            result = "0-1",
            eco = "C48",
            openingName = "Apertura Cuatro Caballos",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "Ronda decisiva del Primer Congreso Americano de 1857. Morphy ejecuta uno de los sacrificios de dama más famosos de la historia para destrozar el enroque de Paulsen y dominar el tablero con sus alfiles coordinados.",
            strategicTheme = "Sacrificio posicional de dama por alfil y peón para crear una red de asedio imparable de piezas menores.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Nc3 Nf6 Bb5 Bc5 O-O O-O Nxe5 Re8 Nxc6 dxc6 Bc4 b5 Be2 Nxe4 Nxe4 Rxe4 Bf3 Re6 c3 Qd3 b4 Bb6 a4 bxa4 Qxa4 Bd7 Ra2 Rae8 Qa6 Qxf3 gxf3 Rg6+ Kh1 Bh3 Rd1 Bg2+ Kg1 Bxf3+ Kf1 Bg2+ Kg1 Bh3+ Kh1 Bxf2 Qf1 Bxf1 Rxf1 Re2 Ra1 Rh6 d4 Be3",
                annotations = mapOf(
            34 to Pair("!!", "¡17...Dxf3!! El sacrificio de dama que asombró al mundo entero."),
            38 to Pair("!", "El alfil negro corta la retirada del rey en g1.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "morphy_paulsen_m17",
                plyBefore = 33,
                title = "El Sacrificio Inmortal en f3",
                prompt = "Las piezas blancas parecen defender el enroque. ¿Cómo destruye Morphy la estructura enemiga de un solo golpe?",
                hint = "Entrega tu pieza de mayor valor en f3 para destrozar los peones del enroque blanco.",
                solutionSan = "Qxf3",
                explanation = "¡17...Dxf3!! Tras 18.gxf3 Tg6+ 19.Rh1 Ah3, las piezas negras tienen una red de mate forzada."
            )
            )
        )
    }

    val game7: BookGame by lazy {
        BookGame(
            id = "schulten_morphy_1857",
            gameNumber = 7,
            title = "Lección Rápida contra el Gambito de Rey",
            whitePlayer = "John William Schulten",
            blackPlayer = "Paul Morphy",
            event = "Nueva York",
            year = "1857",
            result = "0-1",
            eco = "C31",
            openingName = "Contragambito Falkbeer",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "Disputada en Nueva York ante un entusiasta rival romántico. Morphy muestra cómo el Contragambito Falkbeer pulveriza la iniciativa blanca si las blancas descuidan la seguridad de su monarca.",
            strategicTheme = "Castigo implacable al rey rezagado en el centro mediante apertura de líneas.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 f4 d5 exd5 e4 Nc3 Nf6 d3 Bb4 Bd2 e3 Bxe3 O-O Bd2 Bxc3 bxc3 Re8+ Be2 Bg4 c4 c6 dxc6 Nxc6 Kf1 Rxe2 Nxe2 Nd4 Qb1 Bxe2+ Kf2 Ng4+ Kg1 Nf3+ gxf3 Qd4+ Kg2 Qf2+ Kh3 Qxf3+ Kh4 Nh6 Qg1 Nf5+ Kg5 Qh5#",
                annotations = mapOf(
            6 to Pair("!", "Contragambito enérgico en el centro."),
            18 to Pair("!", "Activación de piezas menores con ganancia de tiempo.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "schulten_m12",
                plyBefore = 17,
                title = "Ataque al Rey Expuesto",
                prompt = "¿Cómo mantiene Morphy el asedio sin permitir que el rey blanco respire?",
                hint = "Introduce tu torre con jaque forzado.",
                solutionSan = "Re8+",
                explanation = "La torre entra con fuerza decisiva en la columna e abierta."
            )
            )
        )
    }

    val game8: BookGame by lazy {
        BookGame(
            id = "bird_morphy_1858",
            gameNumber = 8,
            title = "Ruptura Dinámica en Londres",
            whitePlayer = "Henry Edward Bird",
            blackPlayer = "Paul Morphy",
            event = "Londres",
            year = "1858",
            result = "0-1",
            eco = "C41",
            openingName = "Defensa Philidor",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "En su estancia en Londres en 1858, Morphy se enfrentó al formidable Henry Bird. Una partida que ilustra la comprensión superior de Morphy del tiempo y la armonía entre torres y alfiles.",
            strategicTheme = "Sacrificio de calidad para dominar la columna abierta y desmantelar el enroque.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 d6 d4 f5 Nc3 fxe4 Nxe4 d5 Ng3 e4 Ne5 Nf6 Bg5 Bd6 Nh5 O-O Qd2 Qe8 g4 Nxg4 Nxg4 Qxh5 Ne5 Nc6 Be2 Qh3 Nxc6 bxc6 Be3 Rb8 O-O-O Rxf2 Bxf2 Qa3 c3 Qxa2 b4 Qa1+ Kc2 Qa4+ Kb2 Bxb4 cxb4 Rxb4+ Qxb4 Qxb4+ Kc2 e3 Bxe3 Bf5+ Rd3 Qc4+ Kd2 Qa2+ Kd1 Qb1+",
                annotations = mapOf(
            34 to Pair("!!", "¡17...Txf3! Sacrificio de calidad para arrasar los defensores del rey."),
            40 to Pair("!", "Los peones negros avanzan como una apisonadora.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "bird_morphy_m17",
                plyBefore = 38,
                title = "El Golpe Táctico en f3",
                prompt = "Bird ha colocado sus piezas activamente, pero su rey en el enroque es vulnerable. ¿Cómo rompe Morphy?",
                hint = "Sacrifica calidad capturando el caballo de f3 para desatar el ataque de alfil.",
                solutionSan = "b4",
                explanation = "¡17...Txf3! Elimina la pieza defensora clave de las blancas y deja al rey blanco sin amparo."
            )
            )
        )
    }

    val game9: BookGame by lazy {
        BookGame(
            id = "morphy_opera_1858",
            gameNumber = 9,
            title = "La Partida de la Ópera: Desarrollo y Mate",
            whitePlayer = "Paul Morphy",
            blackPlayer = "Duque Karl / Conde Isouard",
            event = "París (Teatro de la Ópera)",
            year = "1858",
            result = "1-0",
            eco = "C41",
            openingName = "Defensa Philidor",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "Jugada en el palco de la Ópera de París durante una representación de El Barbero de Sevilla de Rossini. El epítome del desarrollo rápido, la iniciativa y el sacrificio estético para forzar el mate.",
            strategicTheme = "Desarrollo con ganancia de tiempos, clavada absoluta y sacrificio de dama para mate de torre y alfil.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 d6 d4 Bg4 dxe5 Bxf3 Qxf3 dxe5 Bc4 Nf6 Qb3 Qe7 Nc3 c6 Bg5 b5 Nxb5 cxb5 Bxb5+ Nbd7 O-O-O Rd8 Rxd7 Rxd7 Rd1 Qe6 Bxd7+ Nxd7 Qb8+ Nxb8 Rd8#",
                annotations = mapOf(
            17 to Pair("!", "Sacrificio en b5 para abrir la columna d contra el rey en el centro."),
            31 to Pair("!!", "¡16.Db8+!! Sacrificio de dama para despejar la octava fila hacia el mate con Td8#.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "opera_m16",
                plyBefore = 30,
                title = "El Sacrificio de Dama de la Ópera",
                prompt = "Las negras tienen todas sus piezas atadas. ¿Cómo asesta Morphy el golpe de gracia definitivo?",
                hint = "Sacrifica tu dama en b8 para forzar a la pieza negra a colocarse en la red de mate.",
                solutionSan = "Qb8+",
                explanation = "¡16.Db8+!! Fuerza 16...Cxb8, dejando la octava fila abierta para 17.Td8#."
            )
            )
        )
    }

    val game10: BookGame by lazy {
        BookGame(
            id = "morphy_anderssen_1858",
            gameNumber = 10,
            title = "Duelo de Colosos en París",
            whitePlayer = "Paul Morphy",
            blackPlayer = "Adolf Anderssen",
            event = "París (Match oficial, 9ª)",
            year = "1858",
            result = "1-0",
            eco = "B44",
            openingName = "Defensa Siciliana",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "El choque cumbre entre el rey sin corona de América y el héroe de Europa. Morphy dominó el match con un ajedrez científico y elegante que superó por completo el estilo puramente combinativo de Anderssen.",
            strategicTheme = "Juego dinámico y castigo rápido al retraso en el desarrollo en la Siciliana.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 c5 d4 cxd4 Nf3 Nc6 Nxd4 e6 Nb5 d6 Bf4 e5 Be3 f5 N1c3 f4 Nd5 fxe3 Nbc7+ Kf7 Qf3+ Nf6 Bc4 Nd4 Nxf6+ d5 Bxd5+ Kg6 Qh5+ Kxf6 fxe3 Nxc2+ Ke2",
                annotations = mapOf(
            15 to Pair("!", "Apertura del centro antes de que el rival complete el enroque."),
            31 to Pair("!", "Remate contundente en el flanco de dama.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "morphy_anderssen_m15",
                plyBefore = 31,
                title = "Invasión Letal",
                prompt = "¿Cómo aprovecha Morphy la debilidad de las casillas claras enemigas?",
                hint = "Lleva tu torre a la séptima fila para dominar.",
                solutionSan = "Nxc2+",
                explanation = "La invasión en séptima paraliza por completo la defensa de Anderssen."
            )
            )
        )
    }

    val games: List<BookGame> by lazy {
        listOf(game1, game2, game3, game4, game5, game6, game7, game8, game9, game10)
    }
}
