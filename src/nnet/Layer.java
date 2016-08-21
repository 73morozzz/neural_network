package nnet;
import java.io.Serializable;

import nnet.SigmoidLayer.TypeLayer;

/**
 * ��������� ���������� ����
 */
public interface Layer extends Serializable {
    /**
     * �������� ������ �������� �������
     * @return ������ �������� �������
     */
    int getInputSize();

    /**
     * �������� ������ ����
     * @return ������ ����
     */
    int getSize();
    
    /**
     * �������� ������� ����� ����
     * @return ������� �����
     */
    float[][][] getMatrix();

    /**
     * ��������� ������ ����
     * @param input ������� ������
     * @return �������� ������
     */
    float[] computeOutput(float[] input);
    
    TypeLayer getType();
}