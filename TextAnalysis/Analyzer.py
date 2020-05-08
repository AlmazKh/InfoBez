from chardet.universaldetector import UniversalDetector


def read_file(file_name):
    if file_name == "eng.txt":
        file = open(file_name, "r")
    if file_name == "rus.txt":
        file = open(file_name, "r", encoding="utf-8-sig")
    if file_name == "6-1.txt":
        file = open(file_name, "r")
    if file_name == "14-1.txt":
        file = open(file_name, "r")
    data = file.read()
    sorted_letters = get_sorted_count_of_letters(data)
    w = open(file_name + "_analyzed", "w")
    for k, v in sorted_letters.items():
        s = "'" + str(k) + "'" + " : " + str(v) + "\n"
        w.write(s)


def get_sorted_count_of_letters(data):
    letters = dict()
    for i in range(len(data)):
        if data[i] == "\n":
            continue
        if data[i] not in letters.keys():
            letters[data[i]] = 1
        else:
            letters[data[i]] += 1
    sorted_letters = {k: v for k, v in sorted(letters.items(), key=lambda it_: it_[1], reverse=True)}
    return sorted_letters


def get_encoding_info(file):
    detector = UniversalDetector()
    with open(file, 'rb') as fh:
        for line in fh:
            detector.feed(line)
            if detector.done:
                break
        detector.close()
    print(detector.result)


def create_key_and_decode_6():
    str1 = "Сжг ьвфаф Фбфефвжф э вщ ёэарвг цгавгцфафёр дг зйгшщ ёздезчф. Гвф жгаряг цьшгйвзаф э деэвуафёр зжщмфжр " \
           "ёщху. Гхпявгцщввг дгёащ лфёжпй ёёге ё бзыщб гвф зжщмфаф ёщху лжщвэщб ёжфегчг чфьщжвгчг аэёжяф, " \
           "ягжгепю йефвэаёу з вщщ ц ыщёжувгю ягегхглящ эь-дгш бгвдфвёрщ, еушгб ё яегмщлвгю хзжпаглягю эь-дгш шзйгц. " \
           "Ёжфепю чфьщжвпю аэёжгя бщышз гхоуцащвэубэ, жщащчефббфбэ, дгаэжэягю, йегвэягю э шезчэбэ езя лщагцщлщёяэй " \
           "шщафбэ ьфяатлфа ц ёщхщ дщеа, эьцщёжвпю ц чфьщжфй дгш эбщвщб ёбщёэ. Ц сжгю ёбщёэ, дгш ефёёяфьгб г жгб, " \
           "яфя фбщеэяфвщк дщещйэжеэа фбщеэяфвкф э яфя эьцщёжвфу дщцэкф бэёё Шзхфшгааф Ёцэёж ёощаф хгляз зёжеэк э " \
           "дегмаф, вщ ьфбглэц хгжэвгя, Фвшп, дгбщнфаёу ефёёяфьщк, цщёрбф чгшвпю шау зжщмщвэу Фбфефвжп э шезчэй ыщв " \
           "фежэёжгц. Деэцгыз шгёагцвг сжгж ефёёяфь: «Цвэбфвэт дгежзчфаркщц э эй шглщещю. Ц гшвгб эь чгегшгц Фбщеэяэ, " \
           "гжяепжгю Йеэёжгигегб Ягазбхгб, " \
           "лщагцщягб яефювщ свщечэлвпб э гжцфывпб, ыэа-хпа ёщхщ шгяжге Жфввще. Сжгж Жфввще хпа хгащщ фежэёжгб ц " \
           "ёцгщб егшщ, лщб злщвпб, ф дгжгбз эьцщёжщв ьщбвгбз мфез э Дгежзчфаээ вщ яфя злщвпю, ф яфя фежэёж ц ёцгщб " \
           "егшщ. Хзшзлэ фбщеэяфвкщб, гв ц жг ыщ ёфбгщ цещбу хпа э лщагцщягб, ф щёаэ гв хпа лщагцщягб, жг ефвг эаэ " \
           "дгьшвг гв шгаыщв хпа цатхэжрёу, лжг э ёшщафа гв гшвфышп. Цатхэаёу гв ц гшвз дещяефёвзт фбщеэяфвяз, " \
           "цатхэаёу шг хщьзбэу, яфя фежэёж, цатхэаёу шг жгчг, лжг гшвфышп цбщёжг "
    str2 = "Это знала Амаранта и не сильно волновалась по уходе супруга. Она только вздохнула и принялась утешать " \
           "себя. Обыкновенно после частых ссор с мужем она утешала себя чтением старого газетного листка, " \
           "который хранился у нее в жестяной коробочке из-под монпансье, рядом с крошечной бутылочкой из-под духов. " \
           "Старый газетный листок между объявлениями, телеграммами, политикой, хроникой и другими рук человеческих " \
           "делами заключал в себе перл, известный в газетах под именем смеси. В этой смеси, под рассказом о том, " \
           "как американец перехитрил американца и как известная певица мисс Дубадолла Свист съела бочку устриц и " \
           "прошла, не замочив ботинок, Анды, помещался рассказец, весьма годный для утешения Амаранты и других жен " \
           "артистов. Привожу дословно этот рассказ:" \
           "«Вниманию португальцев и их дочерей. В одном из городов Америки, открытой Христофором Колумбом, " \
           "человеком крайне энергичным и отважным, жил-был себе доктор Таннер. Этот Таннер был более артистом в " \
           "своем роде, чем ученым, а потому известен земному шару и Португалии не как ученый, а как артист в своем " \
           "роде. Будучи американцем, он в то же самое время был и человеком, а если он был человеком, то рано или " \
           "поздно он должен был влюбиться, что и сделал он однажды. Влюбился он в одну прекрасную американку, " \
           "влюбился до безумия, как артист, влюбился до того, что однажды вместо "
    key_dict = dict()
    for i in range(len(str1)):
        if str1[i] not in key_dict.keys():
            key_dict[str1[i]] = str2[i]
    kw = open("6-1.txt_keys", "w")
    for k, v in key_dict.items():
        s = "'" + str(k) + "'" + " -> " + str(v) + "\n"
        kw.write(s)

    decoded = ""
    data = open("6-1.txt").read()
    for i in range(len(data)):
        if data[i] in key_dict.keys():
            decoded += key_dict[data[i]]
        else:
            decoded += data[i]
    w = open("6-1.txt_decrypted", "w", encoding="utf-8")
    print(decoded, file=w)


def decode_14():
    data14 = get_sorted_count_of_letters(open("14-1.txt").read())
    data_eng = get_sorted_count_of_letters(open("eng.txt").read())
    text_key = dict()

    s1 = 0
    for key_14 in data14.keys():
        s2 = 0
        for key_eng in data_eng.keys():
            if s1 == s2:
                text_key[key_14] = key_eng
                break
            s2 += 1
        s1 += 1

    decoded = ""
    data = open("14-1.txt").read()
    for i in range(len(data)):
        if data[i] in text_key.keys():
            decoded += text_key[data[i]]
        else:
            decoded += data[i]
    w = open("14-1.txt_decrypted", "w")
    print(decoded, file=w)


# read_file("eng.txt")
# read_file("rus.txt")
# read_file("6-1.txt")
# read_file("14-1.txt")
get_encoding_info("6-1.txt")
get_encoding_info("14-1.txt")
get_encoding_info("6-1.txt_analyzed")
get_encoding_info("14-1.txt")

decode_14()
get_encoding_info("14-1.txt_decrypted")
# create_key_and_decode_6()
#
# 6-1.txt А.П. Чехов "Жены артистов" http://chehov-lit.ru/chehov/text/zheny-artistov.htm

