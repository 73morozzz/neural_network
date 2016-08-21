package nnet;
import java.io.Serializable;

import nnet.SigmoidLayer.TypeLayer;

/**
 * Интерфейс нейронного слоя
 */
public interface Layer extends Serializable {
    /**
     * Получает размер входного вектора
     * @return Размер входного вектора
     */
    int getInputSize();

    /**
     * Получает размер слоя
     * @return Размер слоя
     */
    int getSize();
    
    /**
     * Получает матрицу весов слоя
     * @return матрица весов
     */
    float[][][] getMatrix();

    /**
     * Вычисляет отклик слоя
     * @param input Входной вектор
     * @return Выходной вектор
     */
    float[] computeOutput(float[] input);
    
    TypeLayer getType();
}