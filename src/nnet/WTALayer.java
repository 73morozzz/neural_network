package nnet;

/**
 * WTA слой
 */
public final class WTALayer {//implements BackpropLayer {
    /**
     *  онструирует WTA слой заданного размера и уровнем довери€
     * @param size –азмер сло€
     * @param minLevel ”ровень довери€
     */
    public WTALayer(int size,float minLevel) {
        // проверки
        if (size < 1) throw new IllegalArgumentException();
        // запомним параметры сло€
        this.size = size;
        this.minLevel = minLevel;
    }

    public int getInputSize() {
        return size;
    }

    public int getSize() {
        return size;
    }

    public float[] computeOutput(float[] input) {
        // проверки
        if (input == null || input.length != size) throw new IllegalArgumentException();

        // найдем победител€
        int winner = 0;
        for (int i = 1; i < size; i++)
            if (input[i] > input[winner]) winner = i;

        // готовим ответ
        float[] output = new float[size];

        // проверим на минимальный уровень расхождени€
        if (minLevel > 0) {
            float level = Float.MAX_VALUE;
            for (int i = 0; i < size; i++)
                if (i != winner && Math.abs(input[i] - input[winner]) < level)
                    level = Math.abs(input[i] - input[winner]);
            if (level < minLevel) return output;
        }

        // говорим кто победитель
        output[winner] = 1;

        // вернем отклик
        return output;
    }

    public void randomize(float min,float max) {
    }

    public float[] computeBackwardError(float[] input,float []error) {
        // проверки
        if (input == null || input.length != size || error == null ||
                error.length != size) throw new IllegalArgumentException();

        // расчитываем ошибку
        float[] backwardError = new float[size];
        float[] output = computeOutput(input);

        for (int i = 0; i < size; i++)
            backwardError[i] = error[i] + output[i] - input[i];

        // вернем вход€щую ошибку
        return backwardError;
    }

    public void adjust(float[] input,float[] error,float rate,float momentum) {
    }

    /**
     * ѕолучает минимальный уровень между победителем и всеми остальными нейронами
     * @return ћинимальный уровень между победителем и всеми остальными нейронами
     */
    public float getMinLevel() {
        return minLevel;
    }

    /**
     * ”станавливает минимальный уровень между победитлем и всеми остальными нейронами
     * @param minLevel Ќовый минимальный уровень
     */
    public void setMinLevel(float minLevel) {
        this.minLevel = minLevel;
    }

    /**
     * –азмер сло€
     */
    private final int size;

    /**
     * ћинимальный уровень между победителем и всеми остальными нейронами
     */
    private float minLevel;
}